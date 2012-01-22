package fspotcloud.model.jpa.tag;

import com.google.inject.Inject;
import com.google.inject.Provider;
import fspotcloud.server.model.api.Tag;
import fspotcloud.server.model.api.Tags;
import fspotcloud.shared.photo.PhotoInfo;
import fspotcloud.shared.photo.PhotoInfoStore;
import fspotcloud.shared.tag.TagNode;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.logging.Logger;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class TagManager implements Tags {

    private static final Logger log = Logger.getLogger(TagManager.class.getName());
    private final Provider<EntityManager> emProvider;
    private final Integer maxDelete;

    @Inject
    public TagManager(Provider<EntityManager> pmProvider,
            @Named("maxDelete") Integer maxDelete) {
        this.emProvider = pmProvider;
        this.maxDelete = maxDelete;
    }

    public List<TagNode> getTags() {
        List<TagNode> result = new ArrayList<TagNode>();
        for (Tag tag : getTagDOList()) {
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

    public List<String> getTagKeys() {
        List<String> result = new ArrayList<String>();
        EntityManager em = emProvider.get();
        em.getTransaction().begin();
        Query query = em.createQuery("select t.id from " + TagEntity.class.getName() + " t");
        List<String> list = (List<String>) query.getResultList();
        result.addAll(list);
        em.getTransaction().commit();
        return result;
    }

    public List<Tag> getTagDOList() {
        EntityManager em = emProvider.get();
        em.getTransaction().begin();
        List<Tag> result = new ArrayList<Tag>();
        Query query = em.createQuery("select OBJECT(t) from " + TagEntity.class.getName() + " t");
        List<TagEntity> extent = query.getResultList();
        for (Tag tag : extent) {
            result.add(tag);
        }
        em.getTransaction().commit();
        return result;
    }

    public Tag getOrNew(String id) {
        Tag tag = getById(id);
        if (tag == null) {
            tag = new TagEntity();
            tag.setId(id);
        }
        return tag;
    }

    public Tag getById(String tagId) {
        EntityManager em = emProvider.get();
        em.getTransaction().begin();
        Tag tag = em.find(TagEntity.class, tagId);
        em.getTransaction().commit();
        return tag;
    }

    public String save(Tag tag) {
        EntityManager em = emProvider.get();
        em.getTransaction().begin();
        em.persist(tag);
        em.getTransaction().commit();
        return tag.getId();
    }

    public void saveAll(List<Tag> tagList) {
        for (Tag tag : tagList) {
            save(tag);
        }
    }

    @Override
    public void deleteAll(List<String> toBoDeleted) {
        for(String key : toBoDeleted) {
            delete(key);
        }
    }

    public void delete(String id) {
        EntityManager em = emProvider.get();
        em.getTransaction().begin();
        Tag tag = em.find(TagEntity.class, id);
        if (tag != null) {
            em.remove(tag);
        }
        em.getTransaction().commit();
    }

    @Override
    public boolean deleteAll() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isEmpty() {
        EntityManager em = emProvider.get();
        em.getTransaction().begin();
        Query query = em.createQuery("select t.id from " + TagEntity.class.getName() + " t");
        query.setMaxResults(1);
        em.getTransaction().commit();
        return ((List<String>) query.getResultList()).isEmpty();
    }
}
