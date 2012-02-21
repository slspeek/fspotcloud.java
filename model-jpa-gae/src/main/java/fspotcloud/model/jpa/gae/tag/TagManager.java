package fspotcloud.model.jpa.gae.tag;

import fspotcloud.model.jpa.tag.TagManagerBase;
import fspotcloud.server.model.api.Tag;
import fspotcloud.server.model.api.Tags;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Provider;
import javax.persistence.EntityManager;

public class TagManager extends TagManagerBase<Tag, TagEntity> implements Tags {

    private static final Logger log = Logger.getLogger(TagManager.class.getName());

    @Inject
    public TagManager(Provider<EntityManager> pmProvider,
            @Named("maxDelete") Integer maxDelete) {
        super(TagEntity.class, pmProvider, maxDelete);
    }

    @Override
    protected Tag newTag() {
        return new TagEntity();
    }

    @Override
    protected Class<? extends Tag> getEntityClass() {
        return TagEntity.class;
    }

  
}
