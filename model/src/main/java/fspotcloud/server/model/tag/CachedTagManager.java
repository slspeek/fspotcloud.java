package fspotcloud.server.model.tag;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang.SerializationUtils;

import net.sf.jsr107cache.Cache;

import com.google.inject.Inject;
import com.google.inject.name.Named;

import fspotcloud.server.model.api.Tag;
import fspotcloud.server.model.api.Tags;
import fspotcloud.shared.tag.TagNode;

public class CachedTagManager implements Tags {
	
	private final Cache cache;
	private final Tags manager;
	private static final String TAG = "TagDO:";
		
	@Inject
	public CachedTagManager(Cache cache, @Named("uncached") Tags tagManager) {
		super();
		this.cache = cache;
		this.manager = tagManager;
	}
	
	@Override
	public List<TagNode> getTags() {
		return manager.getTags();
	}

	@Override
	public Tag getOrNew(String tagId) {
		Tag tag = get(tagId);
		if (tag != null) {
			return tag;
		} else {
			tag = manager.getOrNew(tagId);
			put(tag);
			return tag;
		}
	}

	@Override
	public Tag getById(String tagId) {
		Tag tag = get(tagId);
		if (tag != null) {
			return tag;
		} else {
			tag = manager.getById(tagId);
			put(tag);
			return tag;
		}
	}

	@Override
	public String save(Tag tag) {
		put(tag);
		return manager.save(tag);
	}

	@Override
	public boolean deleteAll() {
		return manager.deleteAll();
	}

	private void put(Tag tag) {
		String id = TAG + tag.getId();
		byte[] serializedTag = SerializationUtils.serialize((Serializable) tag);
		cache.put(id, serializedTag);
	}

	private Tag get(String  tagId) {
		String id = TAG + tagId;
		byte[] serializedTag = (byte[]) cache.get(id);
		if (serializedTag != null) {
			Tag tag = (Tag) SerializationUtils.deserialize(serializedTag);
			return tag;
		} else {
			return null;
		}
	}
}
