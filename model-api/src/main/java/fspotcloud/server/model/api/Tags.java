package fspotcloud.server.model.api;

import java.util.List;

import fspotcloud.shared.tag.TagNode;

public interface Tags {

	List<TagNode> getTags();

	Tag getOrNew(String id);

	Tag getById(String tagId);

	String save(Tag tag);

	void deleteAll(List<String> keys);
	
	boolean deleteAll();
	
	List<String> getTagKeys();
	
	boolean isEmpty();
}