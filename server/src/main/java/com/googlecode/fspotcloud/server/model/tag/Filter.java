package com.googlecode.fspotcloud.server.model.tag;

import com.googlecode.fspotcloud.shared.tag.TagNode;

public interface Filter {
	boolean isValid(TagNode node);
}
