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
            
package com.googlecode.fspotcloud.model.jpa.photo;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.googlecode.fspotcloud.server.model.api.Photo;
import com.googlecode.fspotcloud.server.model.api.PhotoDao;
import com.googlecode.simplejpadao.SimpleDAONamedIdImpl;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;


public abstract class PhotoManagerBase<T extends Photo, U extends T>
    extends SimpleDAONamedIdImpl<Photo, U, String> implements PhotoDao {
    protected final Provider<EntityManager> entityManagerProvider;

    @Inject
    public PhotoManagerBase(Class<U> entityType,
        Provider<EntityManager> emProvider) {
        super(entityType, emProvider);
        this.entityManagerProvider = emProvider;
    }

    protected void detach(Photo photo) {
        List<String> tagList = photo.getTagList();
        photo.setTagList(new ArrayList<String>(tagList));
    }

    protected abstract Photo newPhoto();

    protected abstract Class<?extends Photo> getEntityClass();
}
