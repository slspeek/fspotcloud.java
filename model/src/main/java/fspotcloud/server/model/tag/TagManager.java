package fspotcloud.server.model.tag;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.logging.Logger;

import javax.jdo.Extent;
import javax.jdo.JDOObjectNotFoundException;
import javax.jdo.PersistenceManager;

import com.google.inject.Inject;
import com.google.inject.Provider;

import fspotcloud.server.model.api.Tag;
import fspotcloud.server.model.api.Tags;
import fspotcloud.shared.photo.PhotoInfo;
import fspotcloud.shared.tag.TagNode;

public class TagManager implements Tags {
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
			Extent<TagDO> extent = pm.getExtent(TagDO.class, false);
			for (Tag tag : extent) {
				TagNode node = new TagNode();
				node.setId(tag.getId());
				node.setImportIssued(tag.isImportIssued());
				node.setParentId(tag.getParentId());
				node.setTagName(tag.getTagName());
				SortedSet<PhotoInfo> photoList = tag.getCachedPhotoList();
				if (photoList != null) {
					node.setCachedPhotoList(new ArrayList<PhotoInfo>(photoList));
				} else {
					log.warning("cached photo set was null");
					node.setCachedPhotoList(new ArrayList<PhotoInfo>());
				}
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
			tag = new TagDO();
			tag.setId(id);
		}
		return tag;
	}

	public Tag getById(String tagId) {
		PersistenceManager pm = pmProvider.get();
		Tag tag = null;
		try {
			tag = pm.getObjectById(TagDO.class, tagId);
			SortedSet<PhotoInfo> tagCachedPhotoList = tag.getCachedPhotoList();
			tag = pm.detachCopy(tag);
			tag.setCachedPhotoList(new TreeSet<PhotoInfo>(tagCachedPhotoList));
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
		return tag.getId();
	}
	
	public boolean deleteAll() {
		PersistenceManager pm = pmProvider.get();
		Extent<TagDO> extent = pm.getExtent(TagDO.class);
		List<TagDO> allTags = new ArrayList<TagDO>();
		for (TagDO tag : extent) {
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
