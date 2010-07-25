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

	private final int maxDelete;

	private static final Logger log = Logger.getLogger(PhotoManager.class
			.getName());

	private final Provider<PersistenceManager> pmProvider;

	@Inject
	public PhotoManager(Provider<PersistenceManager> pmProvider,
			@Named("maxDelete") int maxDelete) {
		this.pmProvider = pmProvider;
		this.maxDelete = maxDelete;
	}

	@SuppressWarnings("unchecked")
	public List<String> getPhotoKeysForTag(String tagId) {
		PersistenceManager pm = pmProvider.get();
		try {
			log.warning("TagId: " + tagId);
			List<String> keys = new ArrayList<String>();
			Query query = pm.newQuery(PhotoDO.class);
			query.setFilter("tagList == paramTag "+
					 " && imageLoaded == true");
			query.declareParameters("String paramTag"); 
			query.setOrdering("date");
			query.setRange(0, 30);
			List<PhotoDO> result = (List<PhotoDO>) query.execute(tagId);
			for (PhotoDO photo : result) {
				keys.add(photo.getName());
			}
			return keys;
		} finally {
			pm.close();
		}
	}
	
	public List<PhotoDO> getPhotosForTagAfter(String tagId, Date after, int limit) {
		PersistenceManager pm = pmProvider.get();
		try {
			Query query = pm.newQuery(PhotoDO.class);
			query.setFilter("imageLoaded == true && tagList == '" + tagId
					+ "' && date > dateParam");
			query.declareImports("import java.util.Date");
			query.declareParameters("Date dateParam");
			query.setOrdering("date");
			query.setRange(0, limit);
			List<PhotoDO> photos = (List<PhotoDO>) query.execute(after);
			photos = (List<PhotoDO>) pm.detachCopyAll(photos);
			return photos;
		} finally {
			pm.close();
		}
	}
	
	public List<PhotoDO> getEmptyPhotosForTagAfter(String tagId, Date after, int limit) {
		PersistenceManager pm = pmProvider.get();
		try {
			Query query = pm.newQuery(PhotoDO.class);
			query.setFilter("image == null && tagList == '" + tagId
					+ "' && date > dateParam");
			query.declareImports("import java.util.Date");
			query.declareParameters("Date dateParam");
			query.setOrdering("date");
			query.setRange(0, limit);
			List<PhotoDO> photos = (List<PhotoDO>) query.execute(after);
			photos = (List<PhotoDO>) pm.detachCopyAll(photos);
			return photos;
		} finally {
			pm.close();
		}
	}

	@SuppressWarnings("unchecked")
	public List<PhotoDO> getPhotosStartingAtDate(Date minDate, int limit) {
		PersistenceManager pm = pmProvider.get();
		try {
			Query query = pm.newQuery(PhotoDO.class);
			query.setFilter(" date > dateParam");
			query.declareImports("import java.util.Date");
			query.declareParameters("Date dateParam");
			query.setOrdering("date");
			query.setRange(0, limit);
			List<PhotoDO> result = (List<PhotoDO>) query.execute(minDate);
			
			return (List<PhotoDO>) pm.detachCopyAll(result);
		} finally {
			pm.close();
		}
	}

	public PhotoDO getOrNew(String id) {
		PhotoDO photo = null;
		try {
			photo = getById(id);
		} catch (JDOObjectNotFoundException notExistedYet) {
			photo = new PhotoDO();
			photo.setName(id);
		}
		return photo;
	}

	@SuppressWarnings("unchecked")
	public List<PhotoDO> getOldestPhotosChunk() {
		PersistenceManager pm = pmProvider.get();
		try {
			Query query = pm.newQuery(PhotoDO.class);
			query.setOrdering("date");
			query.setRange(0, maxDelete);
			List<PhotoDO> result = (List<PhotoDO>) query.execute();
			result = (List<PhotoDO>) pm.detachCopyAll(result);
			return result;
		} finally {
			pm.close();
		}
	}

	public void delete(PhotoDO p) {
		PersistenceManager pm = pmProvider.get();
		try {
			pm.deletePersistent(p);
		} finally {
			pm.close();
		}
	}

	public void deleteAll(Collection<PhotoDO> photos) {
		PersistenceManager pm = pmProvider.get();
		try {
			pm.deletePersistentAll(photos);
		} finally {
			pm.close();
		}
	}

	public void save(PhotoDO photo) {
		PersistenceManager pm = pmProvider.get();
		try {
			pm.makePersistent(photo);
		} finally {
			pm.close();
		}
	}

	public void saveAll(List<PhotoDO> photoList) {
		PersistenceManager pm = pmProvider.get();
		try {
			pm.makePersistentAll(photoList);
		} finally {
			pm.close();
		}
	}

	public PhotoDO getById(String id) {
		PersistenceManager pm = pmProvider.get();
		PhotoDO photo = null;
		try {
			photo = pm.getObjectById(PhotoDO.class, id);
			photo = pm.detachCopy(photo);
		} finally {
			pm.close();
		}
		return photo;
	}

}
