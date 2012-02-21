package fspotcloud.server.model.api;

import fspotcloud.simplejpadao.HasSetId;
import fspotcloud.simplejpadao.SimpleDAONamedId;
import java.util.List;

public interface Photos extends SimpleDAONamedId<Photo> {

	Photo getOrNew(String id);

	Photo getById(String id);

}