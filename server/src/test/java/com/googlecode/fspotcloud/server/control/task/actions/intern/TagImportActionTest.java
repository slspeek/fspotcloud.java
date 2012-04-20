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

import com.googlecode.fspotcloud.server.control.task.actions.intern.TagImportAction;

import org.apache.commons.lang.SerializationUtils;

import org.testng.AssertJUnit;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class TagImportActionTest {
    private static final int OFFSET = 0;
    private static final int LIMIT = 1;

    @BeforeMethod
    protected void setUp() throws Exception {
    }


    @Test
    public void testTagImportActionIO() {
        TagImportAction action = new TagImportAction(OFFSET, LIMIT);
        byte[] ser = SerializationUtils.serialize(action);
        TagImportAction des = (TagImportAction)SerializationUtils.deserialize(
                ser);
    }


    @Test
    public void testGetOffset() {
        TagImportAction action = new TagImportAction(OFFSET, LIMIT);
        AssertJUnit.assertEquals(OFFSET, action.getOffset());
    }


    @Test
    public void testGetLimit() {
        TagImportAction action = new TagImportAction(OFFSET, LIMIT);
        AssertJUnit.assertEquals(LIMIT, action.getLimit());
    }


    @Test
    public void testEquals() {
        TagImportAction action = new TagImportAction(OFFSET, LIMIT);
        TagImportAction other = new TagImportAction(OFFSET, LIMIT);
        AssertJUnit.assertEquals(action, other);
        AssertJUnit.assertEquals(other, action);
    }
}
