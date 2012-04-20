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
package com.googlecode.fspotcloud.server.main;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import com.googlecode.fspotcloud.rpc.TagService;
import com.googlecode.fspotcloud.server.model.api.PeerDatabase;
import com.googlecode.fspotcloud.server.model.api.PeerDatabases;
import com.googlecode.fspotcloud.server.model.api.Tag;
import com.googlecode.fspotcloud.server.model.api.Tags;
import com.googlecode.fspotcloud.shared.photo.PhotoInfo;
import com.googlecode.fspotcloud.shared.tag.TagNode;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;


/**
 * The server side implementation of the RPC service.
*/
@SuppressWarnings("serial")
@Singleton
public class TagServiceImpl extends RemoteServiceServlet implements TagService {
    private static final Logger log = Logger.getLogger(
            TagServiceImpl.class.getName());
    @Inject
    private Tags tagManager;
    @Inject
    private PeerDatabases defaultPeer;

    public List<TagNode> loadTagTree() {
        PeerDatabase p = defaultPeer.get();

        if (p.getCachedTagTree() != null) {
            log.info("Got the tree from cache HIT");

            return p.getCachedTagTree();
        } else {
            log.info("Missed the cache; building");

            List<TagNode> tags = tagManager.getTags();
            TreeBuilder builder = new TreeBuilder(tags);
            List<TagNode> tree = builder.getPublicRoots();
            p.setCachedTagTree(tree);
            log.info("Builded, about to save");
            defaultPeer.save(p);

            return tree;
        }
    }


    public List<TagNode> loadAdminTagTree() {
        List<TagNode> tags = tagManager.getTags();
        TreeBuilder builder = new TreeBuilder(tags);
        List<TagNode> tree = builder.getRoots();

        return tree;
    }


    @Override
    public List<String> keysForTag(String tagId) {
        Tag tag = tagManager.find(tagId);
        List<String> result = new ArrayList<String>();

        for (PhotoInfo photo : tag.getCachedPhotoList()) {
            result.add(photo.getId());
        }

        return result;
    }
}
