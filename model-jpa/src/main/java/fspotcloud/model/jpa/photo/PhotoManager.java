package fspotcloud.model.jpa.photo;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;


import com.google.inject.Inject;
import com.google.inject.Provider;

import fspotcloud.server.model.api.Photo;
import fspotcloud.server.model.api.Photos;
import java.util.Arrays;
import javax.persistence.EntityManager;

public class PhotoManager implements Photos {

    @SuppressWarnings("unused")
    private static final Logger log = Logger.getLogger(PhotoManager.class.getName());
    private final Provider<EntityManager> entityManagerProvider;

    @Inject
    public PhotoManager(Provider<EntityManager> pmProvider) {
        this.entityManagerProvider = pmProvider;
    }

    @Override
    public Photo getOrNew(String id) {
        Photo photo = null;
        photo = getById(id);
        if (photo == null) {
            photo = new PhotoEntity();
            photo.setId(id);
            photo.setImageLoaded(false);
            photo.setFullsizeLoaded(false);
            photo.setThumbLoaded(false);
            photo.setTagList(new ArrayList<String>());
        }
        return photo;
    }

    @Override
    public void save(Photo photo) {
        EntityManager em = entityManagerProvider.get();
        em.getTransaction().begin();
        Photo photoBound = em.find(PhotoEntity.class, photo.getId());
        if (photoBound != null) {
            em.merge(photo);
        } else {
            em.persist(photo);
        }
        em.getTransaction().commit();
    }

    @Override
    public void saveAll(List<Photo> photoList) {
        for (Photo photo : photoList) {
            save(photo);
        }
    }

    @Override
    public Photo getById(String id) {
        EntityManager pm = entityManagerProvider.get();
        pm.getTransaction().begin();
        Photo photo = null;
        photo = pm.find(PhotoEntity.class, id);
        pm.getTransaction().commit();
        return photo;
    }

    @Override
    public void deleteAll(List<String> keys) {
        for (String key : keys) {
            delete(key);
        }
    }

    @Override
    public void delete(String id) {
        EntityManager pm = entityManagerProvider.get();
        pm.getTransaction().begin();
        Photo photo = pm.find(PhotoEntity.class, id);
        pm.remove(photo);
        pm.getTransaction().commit();
    }
}
