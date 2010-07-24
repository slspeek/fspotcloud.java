package fspotcloud.server.model.tag;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.Extent;
import javax.jdo.PersistenceManager;

import com.google.inject.Inject;
import com.google.inject.Provider;

import fspotcloud.shared.tag.TagNode;

public class TagReader {

	private final Provider<PersistenceManager> pmProvider;

	@Inject
	public TagReader(Provider<PersistenceManager> pmProvider) {
		this.pmProvider = pmProvider;
	}

	public List<TagNode> getTags() {
		PersistenceManager pm = pmProvider.get();
		try {
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
		} finally {
			pm.close();
		}
	}

	public Tag getById(String tagId) {
		PersistenceManager pm = pmProvider.get();
		Tag tag = null;
		try {
			tag = pm.getObjectById(Tag.class, tagId);
			tag = pm.detachCopy(tag);
		} finally {
			pm.close();
		}
		return tag;
	}

	public String save(Tag tag) {
		PersistenceManager pm = pmProvider.get();
		try {
			pm.makePersistent(tag);
		} finally {
			pm.close();
		}
		return tag.getName();
	}

}
