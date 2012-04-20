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

import com.googlecode.fspotcloud.shared.dashboard.actions.VoidResult;

import net.customware.gwt.dispatch.shared.Action;

import java.io.Serializable;


public class TagImportAction implements Action<VoidResult>, Serializable {
    private static final long serialVersionUID = -8689981785339334225L;
    private final int offset;
    private final int limit;

    public TagImportAction(int offset, int limit) {
        super();
        this.offset = offset;
        this.limit = limit;
    }

    public int getOffset() {
        return offset;
    }


    public int getLimit() {
        return limit;
    }


    @Override
    public boolean equals(Object obj) {
        if (obj instanceof TagImportAction) {
            TagImportAction new_name = (TagImportAction)obj;

            return (new_name.getLimit() == limit)
            && (new_name.getOffset() == offset);
        } else {
            return false;
        }
    }


    public String toString() {
        return "(" + offset + ", " + limit + ")";
    }
}
