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
package com.googlecode.fspotcloud.peer.handlers;

import com.google.common.collect.ImmutableList;
import com.googlecode.fspotcloud.peer.db.Data;
import com.googlecode.fspotcloud.shared.main.PhotoInfo;
import com.googlecode.fspotcloud.shared.peer.GetPeerUpdateInstructionsAction;
import com.googlecode.fspotcloud.shared.peer.PeerUpdateInstructionsResult;
import com.googlecode.fspotcloud.shared.peer.TagData;
import com.googlecode.fspotcloud.shared.peer.TagRemovedFromPeer;
import com.googlecode.fspotcloud.shared.peer.TagUpdate;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.server.SimpleActionHandler;
import net.customware.gwt.dispatch.shared.DispatchException;


public class GetPeerUpdateInstructionsHandler extends SimpleActionHandler<GetPeerUpdateInstructionsAction, PeerUpdateInstructionsResult> {
    private final Data data;

    @Inject
    public GetPeerUpdateInstructionsHandler(Data data) {
        super();
        this.data = data;
    }

    @Override
    public PeerUpdateInstructionsResult execute(
        GetPeerUpdateInstructionsAction action,
        ExecutionContext context) throws DispatchException {
        PeerUpdateInstructionsResult result = new PeerUpdateInstructionsResult(new ArrayList<TagUpdate>(),
                new ArrayList<TagRemovedFromPeer>());

        try {
            List<TagData> peerTagData = data.getTagData();

            for (TagData tagData : action.getTagsOnServer()) {
                String tagId = tagData.getTagId();
                List<TagData> actual = data.getTagData(ImmutableList.of(tagId));

                if (!actual.isEmpty()) {
                    TagData actualData = actual.get(0);

                    if (actualData.equals(tagData)) {
                        peerTagData.remove(tagData);
                    }
                } else {
                    result.getToBoRemovedFromPeer()
                          .add(new TagRemovedFromPeer(tagId));
                }
            }

            for (TagData data : peerTagData) {
                TagUpdate update = new TagUpdate(data.getTagId());
                result.getToBoUpdated().add(update);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
}
