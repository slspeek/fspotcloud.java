package fspotcloud.server.model.api;

import fspotcloud.simplejpadao.SimpleDAONamedId;

public interface Photos extends SimpleDAONamedId<Photo, String> {

	Photo getOrNew(String id);

	Photo getById(String id);

}