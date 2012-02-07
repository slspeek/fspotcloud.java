package fspotcloud.model.jpa.photo;

import com.google.inject.Inject;
import com.google.inject.Provider;
import fspotcloud.server.model.api.Photo;
import java.util.logging.Logger;
import javax.persistence.EntityManager;

public class PhotoManager extends PhotoManagerBase {

    @SuppressWarnings("unused")
    private static final Logger log = Logger.getLogger(PhotoManagerBase.class.getName());

    @Inject
    public PhotoManager(Provider<EntityManager> pmProvider) {
        super(pmProvider);
    }

    @Override
    protected Photo newPhoto() {
        return  new PhotoEntity();
    }

    @Override
    protected Class<? extends Photo> getEntityClass() {
        return PhotoEntity.class;
    }

}
