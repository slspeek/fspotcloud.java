package fspotcloud.server.model.photo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.jdo.JDOObjectNotFoundException;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.name.Named;

import fspotcloud.server.model.api.Photo;
import fspotcloud.server.model.api.Photos;

public class PhotoManager implements Photos {

	private static final Logger log = Logger.getLogger(PhotoManager.class
			.getName());

	private final Provider<PersistenceManager> pmProvider;

	@Inject
	public PhotoManager(Provider<PersistenceManager> pmProvider) {
		this.pmProvider = pmProvider;
	}

	public Photo getOrNew(String id) {
		Photo photo = null;
		try {
			photo = getById(id);
		} catch (JDOObjectNotFoundException notExistedYet) {
			photo = new PhotoDO();
			photo.setId(id);
			photo.setImageLoaded(false);
			photo.setFullsizeLoaded(false);
			photo.setThumbLoaded(false);
			photo.setTagList(new ArrayList<String>());
		}
		return photo;
	}

	public void save(Photo photo) {
		PersistenceManager pm = pmProvider.get();
		try {
			pm.makePersistent(photo);
		} finally {
			pm.close();
		}
	}

	public void saveAll(List<Photo> photoList) {
		PersistenceManager pm = pmProvider.get();
		try {
			pm.makePersistentAll(photoList);
		} finally {
			pm.close();
		}
	}

	public Photo getById(String id) {
		PersistenceManager pm = pmProvider.get();
		Photo photo = null;
		try {
			photo = pm.getObjectById(PhotoDO.class, id);
			photo = pm.detachCopy(photo);
		} finally {
			pm.close();
		}
		return photo;
	}

}
