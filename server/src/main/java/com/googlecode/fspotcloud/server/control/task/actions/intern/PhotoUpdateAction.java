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
import com.googlecode.fspotcloud.shared.peer.PhotoUpdate;
import java.io.Serializable;
import java.util.List;
import net.customware.gwt.dispatch.shared.Action;


public class PhotoUpdateAction implements Action<VoidResult>, Serializable {
    private static final long serialVersionUID = -5470374310534233053L;
    private final List<PhotoUpdate> updates;

    public PhotoUpdateAction(List<PhotoUpdate> updates) {
        super();
        this.updates = updates;
    }

    public List<PhotoUpdate> getUpdates() {
        return updates;
    }

    public String toString() {
        return updates.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PhotoUpdateAction other = (PhotoUpdateAction) obj;
        if (this.updates != other.updates && (this.updates == null || !this.updates.equals(other.updates))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + (this.updates != null ? this.updates.hashCode() : 0);
        return hash;
    }
    
    
}
