package fspotcloud.model.jpa.tag;

import fspotcloud.server.model.api.Tag;
import fspotcloud.server.model.api.Tags;
import fspotcloud.shared.photo.PhotoInfo;
import fspotcloud.shared.photo.PhotoInfoStore;
import fspotcloud.shared.tag.TagNode;
import com.googlecode.simplejpadao.SimpleDAONamedIdImpl;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Provider;
import javax.persistence.EntityManager;

public abstract class TagManagerBase<T extends Tag,U extends T>
extends SimpleDAONamedIdImpl<Tag, U, String> implements Tags {

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

    protected abstract Tag newTag();

    protected abstract Class<? extends Tag> getEntityClass();
}
