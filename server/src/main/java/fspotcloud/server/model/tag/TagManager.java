package fspotcloud.server.model.tag;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.jdo.Extent;
import javax.jdo.JDOObjectNotFoundException;
import javax.jdo.PersistenceManager;

import com.google.inject.Inject;
import com.google.inject.Provider;

import fspotcloud.server.model.photo.Photo;
import fspotcloud.shared.tag.TagNode;

public class TagManager {
	
	private static final Logger log = Logger.getLogger(TagManager.class.getName());

	private final Provider<PersistenceManager> pmProvider;

	@Inject
	public TagManager(Provider<PersistenceManager> pmProvider) {
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

	public Tag getOrNew(String id) {
		Tag tag = null;
		try {
			tag = getById(id);
		} catch (JDOObjectNotFoundException notExistedYet) {
			tag = new Tag();
			tag.setName(id);
		}
		return tag;
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
	
	public boolean deleteAll() {
		PersistenceManager pm = pmProvider.get();
		Extent<Tag> extent = pm.getExtent(Tag.class);
		List<Tag> allTags = new ArrayList<Tag>();
		for (Tag tag : extent) {
			// log.info(tag.toString());
			allTags.add(tag);
		}
		extent.closeAll();
		try {
			pm.deletePersistentAll(allTags);
			log.info("All tags deleted.");
			return true;
		} finally {
			pm.close();
		}
	}

	public void saveAll(List<Tag> tagList) {
		PersistenceManager pm = pmProvider.get();
		try {
			pm.makePersistentAll(tagList);
		} finally {
			pm.close();
		}
	}

}
