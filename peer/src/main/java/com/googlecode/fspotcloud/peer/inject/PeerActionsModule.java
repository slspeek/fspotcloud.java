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
package com.googlecode.fspotcloud.peer.inject;

import com.googlecode.fspotcloud.peer.handlers.GetPeerMetaDataHandler;
import com.googlecode.fspotcloud.peer.handlers.GetPhotoDataHandler;
import com.googlecode.fspotcloud.peer.handlers.GetTagDataHandler;
import com.googlecode.fspotcloud.peer.handlers.GetTagUpdateInstructionsHandler;
import com.googlecode.fspotcloud.shared.peer.rpc.actions.GetPeerMetaData;
import com.googlecode.fspotcloud.shared.peer.rpc.actions.GetPhotoData;
import com.googlecode.fspotcloud.shared.peer.rpc.actions.GetTagData;
import com.googlecode.fspotcloud.shared.peer.rpc.actions.GetTagUpdateInstructionsAction;

import net.customware.gwt.dispatch.server.guice.ActionHandlerModule;


public class PeerActionsModule extends ActionHandlerModule {
    @Override
    protected void configureHandlers() {
        bindHandler(GetPeerMetaData.class, GetPeerMetaDataHandler.class);
        bindHandler(GetTagData.class, GetTagDataHandler.class);
        bindHandler(GetPhotoData.class, GetPhotoDataHandler.class);
        bindHandler(
            GetTagUpdateInstructionsAction.class,
            GetTagUpdateInstructionsHandler.class);
    }
}
