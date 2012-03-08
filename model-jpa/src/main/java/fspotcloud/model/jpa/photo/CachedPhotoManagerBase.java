package fspotcloud.model.jpa.photo;

import java.util.logging.Logger;


import com.google.inject.Inject;
import com.google.inject.Provider;

import fspotcloud.server.model.api.Photo;
import fspotcloud.server.model.api.Photos;
import com.googlecode.simplejpadao.SimpleDAONamedIdImpl;
import com.googlecode.simplejpadao.cacheddao.CachedSimpleDAONamedIdImpl;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import net.sf.jsr107cache.Cache;

public abstract class CachedPhotoManagerBase<T extends Photo, U extends T>
        extends CachedSimpleDAONamedIdImpl<Photo, U, String> implements Photos {

    @SuppressWarnings("unused")
    private static final Logger log = Logger.getLogger(CachedPhotoManagerBase.class.getName());
    protected final Provider<EntityManager> entityManagerProvider;

    @Inject
    public CachedPhotoManagerBase(Class<U> entityType, Cache cache, Provider<EntityManager> emProvider) {
        super(entityType, cache, new SimpleDAONamedIdImpl<Photo, U, String>(entityType, emProvider));
        this.entityManagerProvider = emProvider;
    }

    private void detach(Photo photo) {
        List<String> tagList = photo.getTagList();
        photo.setTagList(new ArrayList<String>(tagList));
    }

    @Override
    public Photo find(String key) {
        EntityManager em = entityManagerProvider.get();
        em.getTransaction().begin();
        Photo attachted = em.find(getEntityClass(), key);
        if (attachted != null) {
            detach(attachted);
        }
        em.getTransaction().commit();
        return attachted;
    }

    @Override
    public List<Photo> findAll(int max) {
        EntityManager em = entityManagerProvider.get();
        em.getTransaction().begin();
        try {
            Query query = em.createQuery("select c from "
                    + getEntityClass().getName() + " AS c");
            query.setMaxResults(max);
            @SuppressWarnings("unchecked")
            List<Photo> rs = (List<Photo>) query.getResultList();
            List<Photo> result = new ArrayList<Photo>();
            result.addAll(rs);
            final List<Photo> all = result;
            for (Photo photo : all) {
                detach(photo);
            }
            em.getTransaction().commit();
            return all;
        } finally {
            em.close();
        }
    }

    protected abstract Photo newPhoto();

    protected abstract Class<? extends Photo> getEntityClass();
}
