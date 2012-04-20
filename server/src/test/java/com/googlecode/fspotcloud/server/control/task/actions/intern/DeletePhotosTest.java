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
package com.googlecode.fspotcloud.server.control.task.actions.intern;

import com.googlecode.fspotcloud.shared.photo.PhotoInfo;

import org.apache.commons.lang.SerializationUtils;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class DeletePhotosTest {
    @BeforeMethod
    protected void setUp() throws Exception {
    }


    @Test
    public void testSerialization() {
        PhotoInfo photoInfoA = new PhotoInfo("", "", new Date(10));
        List<PhotoInfo> list = new ArrayList<PhotoInfo>();
        list.add(photoInfoA);

        DeletePhotos action = new DeletePhotos("fooMock", list);
        byte[] ser = SerializationUtils.serialize(action);
        DeletePhotos deserialized = (DeletePhotos)SerializationUtils.deserialize(
                ser);
    }
}
