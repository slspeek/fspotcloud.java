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

import com.google.common.base.Objects;
import static com.google.common.base.Objects.equal;

import java.io.Serializable;
public class TagData implements Serializable {
    private static final long serialVersionUID = -7990602627338507900L;
    private String tagId;
    private String parentId;
    private String name;
    private int count;

    public TagData(String tagId, String name, String parentId, int count) {
        super();
        this.tagId = tagId;
        this.parentId = parentId;
        this.name = name;
        this.count = count;
    }

    public String getTagId() {
        return tagId;
    }


    public String getParentId() {
        return parentId;
    }


    public String getName() {
        return name;
    }


    public int getCount() {
        return count;
    }


    @Override
    public boolean equals(Object obj) {
        if (obj instanceof TagData) {
            TagData other = (TagData)obj;

            return equal(getTagId(), other.getTagId())
            && equal(getParentId(), other.getParentId())
            && equal(getName(), other.getName())
            && equal(getCount(), other.getCount());
        } else {
            return false;
        }
    }
}
