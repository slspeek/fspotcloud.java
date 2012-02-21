package fspotcloud.model.jpa.tag;

import fspotcloud.server.model.api.Tag;
import fspotcloud.server.model.api.Tags;
import fspotcloud.shared.photo.PhotoInfo;
import fspotcloud.shared.photo.PhotoInfoStore;
import fspotcloud.shared.tag.TagNode;
import fspotcloud.simplejpadao.HasSetId;
import fspotcloud.simplejpadao.SimpleDAONamedIdImpl;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Provider;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public abstract class TagManagerBase<T extends Tag,U extends T>
extends SimpleDAONamedIdImpl<Tag, U> implements Tags {

    private static final Logger log = Logger.getLogger(TagManagerBase.class.getName());
    private final Provider<EntityManager> emProvider;
    private final Integer maxDelete;

    @Inject
    public TagManagerBase(Class<U> entityType, Provider<EntityManager> emProvider,
            @Named("maxDelete") Integer maxDelete) {
        super(entityType, emProvider);
        this.emProvider = emProvider;
        this.maxDelete = maxDelete;
    }

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

    public Tag getOrNew(String id) {
        return findOrNew(id);
    }

    public Tag getById(String tagId) {
        return find(tagId);
    }

   
    public void delete(String id) {
        EntityManager em = emProvider.get();
        em.getTransaction().begin();
        Tag tag = em.find(getEntityClass(), id);
        if (tag != null) {
            em.remove(tag);
        }
        em.getTransaction().commit();
    }

    protected abstract Tag newTag();

    protected abstract Class<? extends Tag> getEntityClass();
}
