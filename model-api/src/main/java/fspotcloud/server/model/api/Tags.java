package fspotcloud.server.model.api;

import fspotcloud.shared.tag.TagNode;
import com.googlecode.simplejpadao.SimpleDAONamedId;
import java.util.List;

public interface Tags extends SimpleDAONamedId<Tag, String> {

	List<TagNode> getTags();
}