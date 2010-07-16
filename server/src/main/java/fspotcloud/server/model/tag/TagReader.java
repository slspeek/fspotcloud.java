package fspotcloud.server.model.tag;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.Extent;
import javax.jdo.PersistenceManager;

import com.google.inject.Inject;

import fspotcloud.shared.tag.TagNode;

public class TagReader {

	
	private final PersistenceManager pm;
	
	@Inject
	public TagReader(PersistenceManager pm) {
		this.pm =pm;
	}
	
	public  List<TagNode> getTags() {
		List<TagNode> result = new ArrayList<TagNode>();
		Extent<Tag> extent = pm.getExtent(Tag.class, false);
		for (Tag tag : extent) {
			TagNode node = new TagNode();
			node.setName(tag.getName());
			node.setParentId(tag.getParentId());
			node.setTagName(tag.getTagName());
			result.add(node);
		}
		extent.closeAll();
		return result;
	}
	
	public Tag getById(String tagId) {
		Tag tag = null;
		tag = pm.getObjectById(Tag.class, tagId);
		return tag;
	}

	public String save(Tag tag) {
		pm.makePersistent(tag);
		return tag.getName();
	}

}
