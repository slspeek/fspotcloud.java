package fspotcloud.server.model.tag;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.Extent;
import javax.jdo.PersistenceManager;

import fspotcloud.server.model.batch.Batch;
import fspotcloud.server.util.PMF;
import fspotcloud.shared.tag.TagNode;

public class TagReader {

	public static List<TagNode> getTags() {
		List<TagNode> result = new ArrayList<TagNode>();
		PersistenceManager pm = PMF.get().getPersistenceManager();
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
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Tag tag = null;
		try {
			tag = pm.getObjectById(Tag.class, tagId);
		} finally {
			pm.close();
		}
		return tag;
	}

	public String save(Tag tag) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try {
			pm.makePersistent(tag);
		} finally {
			pm.close();
		}
		return tag.getName();
	}

}
