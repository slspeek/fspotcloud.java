package com.googlecode.fspotcloud.server.model.api;

import com.googlecode.fspotcloud.shared.tag.TagNode;
import com.googlecode.simplejpadao.SimpleDAONamedId;
import java.util.List;

public interface Tags extends SimpleDAONamedId<Tag, String> {

	List<TagNode> getTags();
        
        List<Tag> getImportedTags();
}