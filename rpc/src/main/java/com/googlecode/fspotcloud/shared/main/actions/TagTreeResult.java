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
package com.googlecode.fspotcloud.shared.main.actions;

import com.google.common.base.Objects;

import com.googlecode.fspotcloud.shared.tag.TagNode;

import net.customware.gwt.dispatch.shared.Result;

import java.util.List;


public class TagTreeResult implements Result {
    private List<TagNode> tree;

    public TagTreeResult() {
    }


    public TagTreeResult(List<TagNode> tree) {
        this.tree = tree;
    }

    public List<TagNode> getTree() {
        return tree;
    }


    @Override
    public boolean equals(Object obj) {
        if (obj instanceof TagTreeResult) {
            TagTreeResult other = (TagTreeResult)obj;
            List<TagNode> otherNodes = other.getTree();

            return Objects.equal(otherNodes, getTree());
        } else {
            return false;
        }
    }
}