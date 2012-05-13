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

import com.googlecode.fspotcloud.shared.dashboard.VoidResult;
import com.googlecode.fspotcloud.shared.main.PhotoInfo;
import java.io.Serializable;
import java.util.List;
import net.customware.gwt.dispatch.shared.Action;


public class RemovePhotosFromTagAction implements Action<VoidResult>,
    Serializable {
    private static final long serialVersionUID = -8353337263892135688L;
    private final String tagId;
    private final List<String> toBeDeleted;

    public RemovePhotosFromTagAction(String tagId, List<String> toBeDeleted) {
        super();
        this.tagId = tagId;
        this.toBeDeleted = toBeDeleted;
    }

    public String getTagId() {
        return tagId;
    }

    public List<String> getToBeDeleted() {
        return toBeDeleted;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final RemovePhotosFromTagAction other = (RemovePhotosFromTagAction) obj;
        if ((this.tagId == null) ? (other.tagId != null) : !this.tagId.equals(other.tagId)) {
            return false;
        }
        if (this.toBeDeleted != other.toBeDeleted && (this.toBeDeleted == null || !this.toBeDeleted.equals(other.toBeDeleted))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 73 * hash + (this.tagId != null ? this.tagId.hashCode() : 0);
        hash = 73 * hash + (this.toBeDeleted != null ? this.toBeDeleted.hashCode() : 0);
        return hash;
    }

    
}
