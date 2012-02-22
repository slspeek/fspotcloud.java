package fspotcloud.model.jpa.gae.photo;

import com.google.inject.Inject;
import com.google.inject.Provider;
import fspotcloud.model.jpa.photo.PhotoManagerBase;
import fspotcloud.server.model.api.Photo;
import fspotcloud.server.model.api.Photos;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class PhotoManager extends PhotoManagerBase<Photo, PhotoEntity> implements Photos {

    @SuppressWarnings("unused")
    private static final Logger log = Logger.getLogger(PhotoManagerBase.class.getName());

    @Inject
    public PhotoManager(Provider<EntityManager> pmProvider) {
        super(PhotoEntity.class, pmProvider);
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
                    + entityType.getName() + " AS c");
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

    @Override
    protected Photo newPhoto() {
        return new PhotoEntity();
    }

    @Override
    protected Class<? extends Photo> getEntityClass() {
        return PhotoEntity.class;
    }
}
