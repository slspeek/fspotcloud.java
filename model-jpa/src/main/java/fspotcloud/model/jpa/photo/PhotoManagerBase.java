package fspotcloud.model.jpa.photo;

import java.util.logging.Logger;


import com.google.inject.Inject;
import com.google.inject.Provider;

import fspotcloud.server.model.api.Photo;
import fspotcloud.server.model.api.Photos;
import fspotcloud.simplejpadao.SimpleDAONamedIdImpl;
import javax.persistence.EntityManager;

public abstract class PhotoManagerBase<T extends Photo, U extends T>
extends SimpleDAONamedIdImpl<Photo, U, String> implements Photos {

    @SuppressWarnings("unused")
    private static final Logger log = Logger.getLogger(PhotoManagerBase.class.getName());
    protected final Provider<EntityManager> entityManagerProvider;

    @Inject
    public PhotoManagerBase(Class<U> entityType,Provider<EntityManager> emProvider) {
        super(entityType, emProvider);
        this.entityManagerProvider = emProvider;
    }

    public Photo getOrNew(String id) {
        final Photo photo = findOrNew(id);
        return photo;
    }

    @Override
    public Photo getById(String id) {
        return find(id);
    }

    
    protected abstract Photo newPhoto();
    
    protected abstract Class<? extends Photo> getEntityClass();
}
