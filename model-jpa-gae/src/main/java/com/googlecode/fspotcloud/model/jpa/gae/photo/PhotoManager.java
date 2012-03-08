package com.googlecode.fspotcloud.model.jpa.gae.photo;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.googlecode.fspotcloud.model.jpa.photo.CachedPhotoManagerBase;
import com.googlecode.fspotcloud.model.jpa.photo.PhotoManagerBase;
import com.googlecode.fspotcloud.server.model.api.Photo;
import com.googlecode.fspotcloud.server.model.api.Photos;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import net.sf.jsr107cache.Cache;

public class PhotoManager extends CachedPhotoManagerBase<Photo, PhotoEntity> implements Photos {

    @SuppressWarnings("unused")
    private static final Logger log = Logger.getLogger(PhotoManagerBase.class.getName());

    
    @Inject
    public PhotoManager(Provider<EntityManager> pmProvider, Cache cache) {
        super(PhotoEntity.class, cache, pmProvider);
    }

    @Override
    protected Photo newPhoto() {
        return new PhotoEntity();
    }

    @Override
    protected Class<? extends Photo> getEntityClass() {
        return PhotoEntity.class;
    }
}
