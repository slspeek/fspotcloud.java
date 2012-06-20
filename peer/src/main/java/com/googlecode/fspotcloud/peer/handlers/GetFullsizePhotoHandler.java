/*
 * Copyright 2010-2012 Steven L. Speek.
 * This program is free software; you can redistribute it
                and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free
                Software Foundation; either version 2
 * of the License, or any later version.
 *
 * This program is
                distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied
                warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public
                License for more details.
 * You should have received a copy of the GNU General Public License
 * along
                with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330,
                Boston, MA 02111-1307, USA.
 *
 */
            
package com.googlecode.fspotcloud.peer.handlers;

import com.google.inject.Inject;
import com.googlecode.fspotcloud.peer.db.Data;
import com.googlecode.fspotcloud.shared.peer.FullsizePhotoResult;
import com.googlecode.fspotcloud.shared.peer.GetFullsizePhotoAction;
import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.server.SimpleActionHandler;
import net.customware.gwt.dispatch.shared.ActionException;
import net.customware.gwt.dispatch.shared.DispatchException;


public class GetFullsizePhotoHandler extends SimpleActionHandler<GetFullsizePhotoAction, FullsizePhotoResult> {
    private final Data data;

    @Inject
    public GetFullsizePhotoHandler(Data data) {
        super();
        this.data = data;
    }

    @Override
    public FullsizePhotoResult execute(GetFullsizePhotoAction action,
        ExecutionContext context) throws DispatchException {
        FullsizePhotoResult result;

        try {
            result = new FullsizePhotoResult(action.getImageKey(),
                    data.getFullsizePhotoData(action.getImageKey()));
        } catch (Exception e) {
            throw new ActionException(e);
        }

        return result;
    }
}
