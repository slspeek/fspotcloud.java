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
package com.googlecode.fspotcloud.model.jpa.tag;

import com.googlecode.fspotcloud.server.model.api.Tag;
import com.googlecode.fspotcloud.server.model.api.Tags;
import com.googlecode.fspotcloud.shared.photo.PhotoInfo;
import com.googlecode.fspotcloud.shared.photo.PhotoInfoStore;
import com.googlecode.fspotcloud.shared.tag.TagNode;

import com.googlecode.simplejpadao.SimpleDAONamedIdImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Provider;

import javax.persistence.EntityManager;
import javax.persistence.Query;


public abstract class TagManagerBase<T extends Tag, U extends T>
    extends SimpleDAONamedIdImpl<Tag, U, String> implements Tags {
    private static final Logger log = Logger.getLogger(
            TagManagerBase.class.getName());

    @Inject
    public TagManagerBase(
        Class<U> entityType, Provider<EntityManager> emProvider,
        @Named("maxDelete")
    Integer maxDelete) {
        super(entityType, emProvider);
    }

    @Override
    public List<TagNode> getTags() {
        List<TagNode> result = new ArrayList<TagNode>();

        for (Tag tag : findAll(1000)) {
            TagNode node = new TagNode();
            node.setId(tag.getId());
            node.setImportIssued(tag.isImportIssued());
            node.setParentId(tag.getParentId());
            node.setTagName(tag.getTagName());
            node.setCount(tag.getCount());

            SortedSet<PhotoInfo> photoList = tag.getCachedPhotoList();

            if (photoList != null) {
                node.setCachedPhotoList(new PhotoInfoStore(photoList));
            } else {
                throw new IllegalStateException(
                    "photoList field of Tag should not be null");
            }

            result.add(node);
        }

        return result;
    }


    @Override
    public List<Tag> getImportedTags() {
        EntityManager em = entityManagerProvider.get();
        em.getTransaction().begin();

        try {
            Query query = em.createQuery(
                    "select c from " + entityType.getName()
                    + " AS c WHERE importIssued = true ");
            @SuppressWarnings("unchecked")
            List<Tag> rs = (List<Tag>)query.getResultList();
            List<Tag> result = new ArrayList<Tag>();
            result.addAll(rs);
            em.getTransaction().commit();

            return result;
        } finally {
            em.close();
        }
    }


    protected abstract Tag newTag();


    protected abstract Class<?extends Tag> getEntityClass();
}
