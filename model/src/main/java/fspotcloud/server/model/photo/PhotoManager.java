package fspotcloud.server.model.photo;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.jdo.JDOObjectNotFoundException;
import javax.jdo.PersistenceManager;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.inject.Inject;
import com.google.inject.Provider;

import fspotcloud.server.model.api.Photo;
import fspotcloud.server.model.api.Photos;

public class PhotoManager implements Photos {

	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(PhotoManager.class
			.getName());

	private final Provider<PersistenceManager> pmProvider;
	DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();	
	
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

	@Override
	public void deleteAll(List<String> keys) {
		List<Key> keyList = new ArrayList<Key>();
		for(String keyString: keys) {
			Key key = KeyFactory.createKey("PhotoDO", keyString);
			keyList.add(key);
		}
		datastore.delete(keyList);	
	}

}
