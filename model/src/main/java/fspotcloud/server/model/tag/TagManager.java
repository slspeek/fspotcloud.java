package fspotcloud.server.model.tag;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.logging.Logger;

import javax.inject.Named;
import javax.jdo.Extent;
import javax.jdo.JDOObjectNotFoundException;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.inject.Inject;
import com.google.inject.Provider;

import fspotcloud.server.model.api.Tag;
import fspotcloud.server.model.api.Tags;
import fspotcloud.shared.photo.PhotoInfo;
import fspotcloud.shared.photo.PhotoInfoStore;
import fspotcloud.shared.tag.TagNode;

public class TagManager implements Tags {
	private static final Logger log = Logger.getLogger(TagManager.class
			.getName());

	private final Provider<PersistenceManager> pmProvider;
	DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();	

	private final Integer maxDelete;

	@Inject
	public TagManager(Provider<PersistenceManager> pmProvider,
			@Named("maxDelete") Integer maxDelete) {
		this.pmProvider = pmProvider;
		this.maxDelete = maxDelete;
	}

	public List<TagNode> getTags() {
		List<TagNode> result = new ArrayList<TagNode>();
		for (Tag tag : getTagDOList()) {
			TagNode node = new TagNode();
			node.setId(tag.getId());
			node.setImportIssued(tag.isImportIssued());
			node.setParentId(tag.getParentId());
			node.setTagName(tag.getTagName());
			node.setCount(tag.getCount());
			SortedSet<PhotoInfo> photoList = tag.getCachedPhotoList();
			if (photoList != null) {
				node.setCachedPhotoList(new PhotoInfoStore(photoList));
			} else {
				throw new IllegalStateException(
						"photoList field of Tag should not be null");
			}
			result.add(node);
		}

		return result;
	}

	public List<String> getTagKeys() {
		List<String> result = new ArrayList<String>();
		PersistenceManager pm = pmProvider.get();
		try {
			Query query = pm.newQuery("select id from "+ TagDO.class.getName());
			query.setRange(0, maxDelete);
			List<String> list = (List<String>) query.execute();
			result.addAll(list);
			return result;
		} finally {
			pm.close();
		}
	}

	public List<Tag> getTagDOList() {
		PersistenceManager pm = pmProvider.get();
		try {
			List<Tag> result = new ArrayList<Tag>();
			Extent<TagDO> extent = pm.getExtent(TagDO.class, false);
			for (Tag tag : extent) {
				tag = detach(pm, tag);
				result.add(tag);
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
			tag = detach(pm, tag);
		} finally {
			pm.close();
		}
		return tag;
	}

	private Tag detach(PersistenceManager pm, Tag tag) {
		SortedSet<PhotoInfo> tagCachedPhotoList = tag.getCachedPhotoList();
		tag = pm.detachCopy(tag);
		tag.setCachedPhotoList(new TreeSet<PhotoInfo>(tagCachedPhotoList));
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

	
	public void saveAll(List<Tag> tagList) {
		PersistenceManager pm = pmProvider.get();
		try {
			pm.makePersistentAll(tagList);
		} finally {
			pm.close();
		}
	}

	public void deleteAll(List<String> toBoDeleted) {
		List<Key> keyList = new ArrayList<Key>();
		for(String keyString: toBoDeleted) {
			Key key = KeyFactory.createKey("TagDO", keyString);
			keyList.add(key);
		}
		datastore.delete(keyList);	
	}

	
	@Override
	public boolean deleteAll() {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean isEmpty() {
		PersistenceManager pm = pmProvider.get();
		try {
			Query query = pm.newQuery("select id from "+ TagDO.class.getName());
			query.setRange(0, 1);
			return ((List<String>) query.execute()).isEmpty();
		} finally {
			pm.close();
		}
	}
}
