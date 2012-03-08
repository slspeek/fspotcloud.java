package com.googlecode.fspotcloud.model.jpa.gae.tag;

import com.googlecode.fspotcloud.model.jpa.tag.CachedTagManagerBase;
import com.googlecode.fspotcloud.server.model.api.Tag;
import com.googlecode.fspotcloud.server.model.api.Tags;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Provider;
import javax.persistence.EntityManager;
import net.sf.jsr107cache.Cache;

public class TagManager extends CachedTagManagerBase<Tag, TagEntity> implements Tags {

    private static final Logger log = Logger.getLogger(TagManager.class.getName());

    @Inject
    public TagManager(Provider<EntityManager> pmProvider,
            @Named("maxDelete") Integer maxDelete, Cache cache) {
        super(TagEntity.class, pmProvider, maxDelete, cache);
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
