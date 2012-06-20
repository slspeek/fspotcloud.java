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
            
package com.googlecode.fspotcloud.model.jpa.gae.tag;

import com.googlecode.fspotcloud.model.jpa.tag.CachedTagManagerBase;
import com.googlecode.fspotcloud.server.model.api.Tag;
import com.googlecode.fspotcloud.server.model.api.TagDao;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Provider;
import javax.persistence.EntityManager;
import net.sf.jsr107cache.Cache;


public class TagManager extends CachedTagManagerBase<Tag, TagEntity>
    implements TagDao {
    private static final Logger log = Logger.getLogger(TagManager.class.getName());

    @Inject
    public TagManager(Provider<EntityManager> pmProvider,
        @Named("maxDelete")
    Integer maxDelete, Cache cache) {
        super(TagEntity.class, pmProvider, maxDelete, cache);
    }

    @Override
    protected Tag newTag() {
        return new TagEntity();
    }

    @Override
    protected Class<?extends Tag> getEntityClass() {
        return TagEntity.class;
    }
}
