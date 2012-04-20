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

import net.customware.gwt.dispatch.shared.Action;

import java.io.Serializable;


public class GetTagData implements Action<TagDataResult>, Serializable {
    private static final long serialVersionUID = -2428269504170714946L;
    private int offset;
    private int count;

    public GetTagData(int offset, int count) {
        super();
        this.offset = offset;
        this.count = count;
    }

    public int getOffset() {
        return offset;
    }


    public int getCount() {
        return count;
    }
}
