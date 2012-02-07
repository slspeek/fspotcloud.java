package fspotcloud.model.jpa.tag;

import com.google.inject.Inject;
import com.google.inject.Provider;
import fspotcloud.server.model.api.Tag;
import java.util.logging.Logger;
import javax.inject.Named;
import javax.persistence.EntityManager;

public class TagManager extends TagManagerBase {

    private static final Logger log = Logger.getLogger(TagManager.class.getName());

    @Inject
    public TagManager(Provider<EntityManager> pmProvider,
            @Named("maxDelete") Integer maxDelete) {
        super(pmProvider, maxDelete);
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
