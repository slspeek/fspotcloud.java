package fspotcloud.server.model.photo;

import java.io.Serializable;
import java.util.List;

import net.sf.jsr107cache.Cache;

import org.apache.commons.lang.SerializationUtils;

import com.google.inject.Inject;
import com.google.inject.name.Named;

import fspotcloud.server.model.api.Photo;
import fspotcloud.server.model.api.Photos;

public class CachedPhotoManager implements Photos {

	private final Cache cache;
	private final Photos manager;
	private static final String PHOTO_PREFIX = "PhotoDO:";

	@Inject
	public CachedPhotoManager(Cache cache, @Named("uncached") Photos manager) {
		super();
		this.cache = cache;
		this.manager = manager;
	}

	@Override
	public Photo getOrNew(String id) {
		Photo photo = get(id);
		if (photo != null) {
			return photo;
		} else {
			photo = manager.getOrNew(id);
			put(photo);
			return photo;
		}
	}

	@Override
	public void save(Photo photo) {
		put(photo);
		manager.save(photo);
	}

	@Override
	public void saveAll(List<Photo> photoList) {
		for(Photo photo: photoList) {
			put(photo);
		}
		manager.saveAll(photoList);
	}

	@Override
	public Photo getById(String id) {
		Photo photo = get(id);
		if (photo != null) {
			return photo;
		} else {
			photo = manager.getById(id);
			put(photo);
			return photo;
		}
	}

	private void put(Photo photo) {
		String id = PHOTO_PREFIX + photo.getId();
		byte[] serializedTag = SerializationUtils
				.serialize((Serializable) photo);
		cache.put(id, serializedTag);
	}

	private Photo get(String photoId) {
		String id = PHOTO_PREFIX + photoId;
		byte[] serializedTag = (byte[]) cache.get(id);
		if (serializedTag != null) {
			Photo photo = (Photo) SerializationUtils.deserialize(serializedTag);
			return photo;
		} else {
			return null;
		}
	}

}
