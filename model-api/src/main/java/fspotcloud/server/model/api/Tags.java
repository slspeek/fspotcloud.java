package fspotcloud.server.model.api;

import fspotcloud.shared.tag.TagNode;
import fspotcloud.simplejpadao.HasSetId;
import fspotcloud.simplejpadao.SimpleDAONamedId;
import java.util.List;

public interface Tags extends SimpleDAONamedId<Tag> {

	List<TagNode> getTags();

	Tag getOrNew(String id);

	Tag getById(String tagId);
}