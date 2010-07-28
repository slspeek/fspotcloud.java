package fspotcloud.server.model.api;

import java.util.List;

import fspotcloud.shared.tag.TagNode;

public interface Tags {

	public abstract List<TagNode> getTags();

	public abstract Tag getOrNew(String id);

	public abstract Tag getById(String tagId);

	public abstract String save(Tag tag);

	public abstract boolean deleteAll();

	public abstract void saveAll(List<Tag> tagList);

}