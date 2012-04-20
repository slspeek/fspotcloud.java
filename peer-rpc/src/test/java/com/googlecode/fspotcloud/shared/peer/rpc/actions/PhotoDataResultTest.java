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
package com.googlecode.fspotcloud.shared.peer.rpc.actions;

import com.googlecode.fspotcloud.shared.peer.rpc.actions.PhotoData;
import com.googlecode.fspotcloud.shared.peer.rpc.actions.PhotoDataResult;

import junit.framework.TestCase;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class PhotoDataResultTest extends TestCase {
    private static final byte[] IMAGE_DATA = new byte[] { 0, 1 };
    private static final byte[] THUMB_DATA = new byte[] { 0 };
    private static final String PHOTO_ID = "1";
    PhotoDataResult result;

    @Override
    protected void setUp() throws Exception {
        List<String> tags = new ArrayList<String>();
        tags.add("TAG");

        PhotoData p1 = new PhotoData(
                PHOTO_ID, "Story", new Date(10), IMAGE_DATA, THUMB_DATA, tags,
                10);
        List<PhotoData> list = new ArrayList<PhotoData>();
        list.add(p1);
        result = new PhotoDataResult(list);
        super.setUp();
    }


    public void testSerialize() throws Exception {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(bos);
        out.writeObject(result);
        out.close();
    }


    public void testData() {
        String id = result.getPhotoDataList().get(0).getPhotoId();
        assertEquals(PHOTO_ID, id);
    }
}
