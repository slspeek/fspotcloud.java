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
import com.googlecode.fspotcloud.shared.peer.rpc.actions.TagUpdate;

import net.customware.gwt.dispatch.shared.Action;

import java.io.Serializable;

import java.util.List;


public class TagUpdateAction implements Action<VoidResult>, Serializable {
    private static final long serialVersionUID = -5470374310534233053L;
    private final List<TagUpdate> updates;

    public TagUpdateAction(List<TagUpdate> updates) {
        super();
        this.updates = updates;
    }

    public List<TagUpdate> getUpdates() {
        return updates;
    }


    public String toString() {
        return updates.toString();
    }
}