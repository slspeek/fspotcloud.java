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

public class PhotoManager {

	private final int MAX_DELETE;

	private static final Logger log = Logger.getLogger(PhotoManager.class
			.getName());

	private final Provider<PersistenceManager> pmProvider;

	@Inject
	public PhotoManager(Provider<PersistenceManager> pmProvider,
			@Named("maxDelete") int maxDelete) {
		this.pmProvider = pmProvider;
		this.MAX_DELETE = maxDelete;
	}

	@SuppressWarnings("unchecked")
	public List<String> getPhotoKeysForTag(String tagId) {
		PersistenceManager pm = pmProvider.get();
		try {
			log.warning("TagId: " + tagId);
			List<String> keys = new ArrayList<String>();
			Query query = pm.newQuery(Photo.class);
			query.setFilter("tagList == paramTag");
			query.declareParameters("String paramTag");// +
			// " && imageLoaded==True");
			query.setOrdering("date");
			query.setRange(0, 30);
			List<Photo> result = (List<Photo>) query.execute(tagId);
			for (Photo photo : result) {
				keys.add(photo.getName());
			}
			return keys;
		} finally {
			pm.close();
		}

	}

	@SuppressWarnings("unchecked")
	public List<Photo> getPhotosStartingAtDate(Date minDate, int limit) {
		PersistenceManager pm = pmProvider.get();
		try {
			Query query = pm.newQuery(Photo.class);
			query.setFilter(" date > dateParam");
			query.declareImports("import java.util.Date");
			query.declareParameters("Date dateParam");
			query.setOrdering("date");
			query.setRange(0, limit);
			List<Photo> result = (List<Photo>) query.execute(minDate);
			
			return (List<Photo>) pm.detachCopyAll(result);
		} finally {
			pm.close();
		}

	}

	public Photo getOrNew(String id) {
		Photo photo = null;
		try {
			photo = getById(id);
		} catch (JDOObjectNotFoundException notExistedYet) {
			photo = new Photo();
			photo.setName(id);
		}
		return photo;
	}

	@SuppressWarnings("unchecked")
	public List<Photo> getOldestPhotosChunk() {
		PersistenceManager pm = pmProvider.get();
		try {
			Query query = pm.newQuery(Photo.class);
			query.setOrdering("date");
			query.setRange(0, MAX_DELETE);
			List<Photo> result = (List<Photo>) query.execute();
			return result;
		} finally {
			pm.close();
		}

	}

	public void delete(Photo p) {
		PersistenceManager pm = pmProvider.get();
		try {
			pm.deletePersistent(p);
		} finally {
			pm.close();
		}

	}

	public void deleteAll(Collection<Photo> photos) {
		PersistenceManager pm = pmProvider.get();
		try {
			pm.deletePersistentAll(photos);
		} finally {
			pm.close();
		}

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
			photo = pm.getObjectById(Photo.class, id);
		} finally {
			pm.close();
		}
		return photo;

	}

}
