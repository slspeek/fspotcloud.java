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
package com.googlecode.fspotcloud.server.main.handler;

import com.googlecode.fspotcloud.server.model.api.PeerDatabase;
import com.googlecode.fspotcloud.server.model.api.PeerDatabases;
import com.googlecode.fspotcloud.server.model.api.Tags;
import com.googlecode.fspotcloud.server.model.tag.TreeBuilder;
import com.googlecode.fspotcloud.shared.main.GetTagTreeAction;
import com.googlecode.fspotcloud.shared.main.TagNode;
import com.googlecode.fspotcloud.shared.main.TagTreeResult;
import java.util.List;
import java.util.logging.Logger;
import javax.inject.Inject;
import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.server.SimpleActionHandler;
import net.customware.gwt.dispatch.shared.DispatchException;


public class GetTagTreeHandler extends SimpleActionHandler<GetTagTreeAction, TagTreeResult> {
    @SuppressWarnings("unused")
    private static final Logger log = Logger.getLogger(GetTagTreeHandler.class.getName());
    private final PeerDatabases peerDatabases;
    private final Tags tagManager;

    @Inject
    public GetTagTreeHandler(PeerDatabases peerDatabases, Tags tagManager) {
        this.peerDatabases = peerDatabases;
        this.tagManager = tagManager;
    }

    @Override
    public TagTreeResult execute(GetTagTreeAction action,
        ExecutionContext context) throws DispatchException {
        PeerDatabase p = peerDatabases.get();

        if (p.getCachedTagTree() != null) {
            log.info("Got the tree from cache HIT");

            return new TagTreeResult(p.getCachedTagTree());
        } else {
            log.info("Missed the cache; building");

            List<TagNode> tags = tagManager.getTags();
            TreeBuilder builder = new TreeBuilder(tags);
            List<TagNode> tree = builder.getPublicRoots();
            p.setCachedTagTree(tree);
            log.info("Builded, about to save");
            peerDatabases.save(p);

            return new TagTreeResult(tree);
        }
    }
}
