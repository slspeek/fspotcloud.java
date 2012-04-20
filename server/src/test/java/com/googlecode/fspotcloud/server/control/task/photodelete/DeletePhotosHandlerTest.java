/*
 * Copyright 2010-2012 Steven L. Speek.
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 *
 */
package com.googlecode.fspotcloud.server.control.task.photodelete;

import com.google.common.collect.ImmutableList;

import com.googlecode.fspotcloud.model.jpa.photo.PhotoEntity;
import com.googlecode.fspotcloud.model.jpa.tag.TagEntity;
import com.googlecode.fspotcloud.server.control.task.actions.intern.DeletePhotos;
import com.googlecode.fspotcloud.server.model.api.Photo;
import com.googlecode.fspotcloud.server.model.api.Photos;
import com.googlecode.fspotcloud.server.model.api.Tag;
import com.googlecode.fspotcloud.server.model.api.Tags;
import com.googlecode.fspotcloud.shared.photo.PhotoInfo;

import com.googlecode.taskqueuedispatch.TaskQueueDispatch;

import net.customware.gwt.dispatch.shared.DispatchException;

import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import org.mockito.MockitoAnnotations;

import org.testng.AssertJUnit;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TreeSet;


public class DeletePhotosHandlerTest {
    private static final String ID_B = "B";
    private static final String ID_A = "A";
    private static final String TAG_ID = "1";
    int MAX_DELETE_TICKS = 1;
    @Mock
    TaskQueueDispatch dispatchAsync;
    @Mock
    Photos photos;
    @Mock
    Tags tagManager;
    Tag tag;
    Tag tag3;
    @Captor
    ArgumentCaptor<DeletePhotos> nextCallCaptor;
    PhotoInfo photoInfoA;
    PhotoInfo photoInfoB;
    List<PhotoInfo> infoList;
    Photo photoA;
    Photo photoB;
    List<String> tagIdListA = ImmutableList.of("1");
    List<String> tagIdListB = ImmutableList.of("1", "3");
    DeletePhotosHandler handler;

    @BeforeMethod
    protected void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        handler = new DeletePhotosHandler(
                MAX_DELETE_TICKS, dispatchAsync, photos, tagManager);
        tag3 = new TagEntity();
        tag3.setId("3");
        tag3.setImportIssued(true);
        tag = new TagEntity();
        tag.setId(TAG_ID);
        photoA = new PhotoEntity();
        photoA.setTagList(tagIdListA);
        photoB = new PhotoEntity();
        photoB.setTagList(tagIdListB);
        photoInfoA = new PhotoInfo(ID_A, "", new Date(10));
        photoInfoB = new PhotoInfo(ID_B, "", new Date(100000));
        infoList = new ArrayList<PhotoInfo>();
        infoList.add(photoInfoA);
        infoList.add(photoInfoB);

        TreeSet<PhotoInfo> cached = new TreeSet<PhotoInfo>();
        cached.add(photoInfoA);
        cached.add(photoInfoB);
        tag.setCachedPhotoList(cached);
        when(tagManager.find(TAG_ID)).thenReturn(tag);
        when(tagManager.find("3")).thenReturn(tag3);
        when(photos.find(ID_A)).thenReturn(photoA);
        when(photos.find(ID_B)).thenReturn(photoB);
    }


    /**
     * First gets deleted, the second is needed in another tag. The
     * max_delete = 1 so this takes 'recursion' over asyncDispatch.
     *
     * @throws DispatchException
     */
    @Test
    public void testExecute() throws DispatchException {
        AssertJUnit.assertEquals(2, tag.getCachedPhotoList().size());
        handler.execute(new DeletePhotos(TAG_ID, infoList), null);
        verify(photos).delete(photoA);
        verify(photos).find(ID_A);
        AssertJUnit.assertEquals(1, tag.getCachedPhotoList().size());
        verifyNoMoreInteractions(photos);
        verify(dispatchAsync).execute(nextCallCaptor.capture());
        AssertJUnit.assertEquals(TAG_ID, nextCallCaptor.getValue().getTagId());
        handler.execute(new DeletePhotos(TAG_ID, infoList), null);
        verifyNoMoreInteractions(dispatchAsync);
        verify(photos).find(ID_B);
        verifyNoMoreInteractions(photos);
        AssertJUnit.assertEquals(1, tag.getCachedPhotoList().size());
    }
}
