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
package com.googlecode.fspotcloud.shared.main;

import com.google.common.collect.ImmutableSortedSet;
import com.googlecode.fspotcloud.shared.main.PhotoInfo;
import com.googlecode.fspotcloud.shared.main.PhotoInfoStore;
import com.googlecode.fspotcloud.shared.main.TagNode;
import java.util.Date;
import junit.framework.TestCase;


public class TagNodeTest extends TestCase {
    TagNode node;

    protected void setUp() throws Exception {
        super.setUp();
        node = new TagNode();
        node.setTagName("Friends");
        node.setDescription("Friends and family");
        node.setId("1");

        PhotoInfo man = new PhotoInfo("2", "Human", new Date());
        ;

        PhotoInfoStore store = new PhotoInfoStore(ImmutableSortedSet.of(man));
        node.setCachedPhotoList(store);
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testEqualsObject() {
        TagNode other = new TagNode();
        other.setId("1");
        assertEquals(node, other);
    }

    public void testConstructor() {
        TagNode t = new TagNode("1", "0");
        assertEquals("1", t.getId());
        assertEquals("0", t.getParentId());
    }

    public void testToString() {
        String s = node.toString();
        System.out.println(s);
    }
}
