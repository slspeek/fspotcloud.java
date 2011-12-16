package fspotcloud.server.model.tag;

import fspotcloud.shared.tag.TagNode;

public interface Filter {
	boolean isValid(TagNode node);
}
