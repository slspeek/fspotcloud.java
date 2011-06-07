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

	/* (non-Javadoc)
	 * @see fspotcloud.server.model.photo.Photos#getPhotoKeysForTag(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public List<String> getPhotoKeysForTag(String tagId) {
		PersistenceManager pm = pmProvider.get();
		try {
			log.warning("TagId: " + tagId);
			List<String> keys = new ArrayList<String>();
			Query query = pm.newQuery(PhotoDO.class);
			query.setFilter("tagList == paramTag " + " && imageLoaded == true");
			query.declareParameters("String paramTag");
			query.setOrdering("date");
			query.setRange(0, 30);
			List<PhotoDO> result = (List<PhotoDO>) query.execute(tagId);
			for (Photo photo : result) {
				keys.add(photo.getId());
			}
			return keys;
		} finally {
			pm.close();
		}
	}

	/* (non-Javadoc)
	 * @see fspotcloud.server.model.photo.Photos#getPhotosForTagAfter(java.lang.String, java.util.Date, int)
	 */
	public List<Photo> getPhotosForTagAfter(String tagId, Date after, int limit) {
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
			List<Photo> result = new ArrayList<Photo>(photos);
			return result;
		} finally {
			pm.close();
		}
	}

	/* (non-Javadoc)
	 * @see fspotcloud.server.model.photo.Photos#getEmptyPhotosForTagAfter(java.lang.String, java.util.Date, int)
	 */
	@SuppressWarnings("unchecked")
	public List<Photo> getEmptyPhotosForTagAfter(String tagId, Date after,
			int limit) {
		PersistenceManager pm = pmProvider.get();
		try {
			Query query = pm.newQuery(PhotoDO.class);
			query.setFilter("imageLoaded == False && tagList == '" + tagId
					+ "' && date > dateParam");
			query.declareImports("import java.util.Date");
			query.declareParameters("Date dateParam");
			query.setOrdering("date");
			query.setRange(0, limit);
			log.info("Querying:: after: " + after + " tagId: " + tagId);
			List<PhotoDO> photos = (List<PhotoDO>) query.execute(after);
			photos = (List<PhotoDO>) pm.detachCopyAll(photos);
			List<Photo> result = new ArrayList<Photo>(photos);
			log.info("Empty photo-list: " + result);
			return result;

		} finally {
			pm.close();
		}
	}

	/* (non-Javadoc)
	 * @see fspotcloud.server.model.photo.Photos#getPhotosStartingAtDate(java.util.Date, int)
	 */
	@SuppressWarnings("unchecked")
	public List<Photo> getPhotosStartingAtDate(Date minDate, int limit) {
		PersistenceManager pm = pmProvider.get();
		try {
			Query query = pm.newQuery(PhotoDO.class);
			query.setFilter(" date > dateParam");
			query.declareImports("import java.util.Date");
			query.declareParameters("Date dateParam");
			query.setOrdering("date");
			query.setRange(0, limit);
			List<PhotoDO> photos = (List<PhotoDO>) query.execute(minDate);
			photos = (List<PhotoDO>) pm.detachCopyAll(photos);
			List<Photo> result = new ArrayList<Photo>(photos);
			return result;
		} finally {
			pm.close();
		}
	}

	/* (non-Javadoc)
	 * @see fspotcloud.server.model.photo.Photos#getOrNew(java.lang.String)
	 */
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

	/* (non-Javadoc)
	 * @see fspotcloud.server.model.photo.Photos#getOldestPhotosChunk()
	 */
	@SuppressWarnings("unchecked")
	public List<Photo> getOldestPhotosChunk() {
		PersistenceManager pm = pmProvider.get();
		try {
			Query query = pm.newQuery(PhotoDO.class);
			query.setOrdering("date");
			query.setRange(0, maxDelete);
			List<PhotoDO> photos = (List<PhotoDO>) query.execute();
			photos = (List<PhotoDO>) pm.detachCopyAll(photos);
			List<Photo> result = new ArrayList<Photo>(photos);
			return result;
		} finally {
			pm.close();
		}
	}

	/* (non-Javadoc)
	 * @see fspotcloud.server.model.photo.Photos#delete(fspotcloud.server.model.photo.Photo)
	 */
	public void delete(Photo p) {
		PersistenceManager pm = pmProvider.get();
		try {
			pm.deletePersistent(p);
		} finally {
			pm.close();
		}
	}

	/* (non-Javadoc)
	 * @see fspotcloud.server.model.photo.Photos#deleteAll(java.util.Collection)
	 */
	public void deleteAll(Collection<Photo> photos) {
		PersistenceManager pm = pmProvider.get();
		try {
			pm.deletePersistentAll(photos);
		} finally {
			pm.close();
		}
	}

	/* (non-Javadoc)
	 * @see fspotcloud.server.model.photo.Photos#save(fspotcloud.server.model.photo.Photo)
	 */
	public void save(Photo photo) {
		PersistenceManager pm = pmProvider.get();
		try {
			pm.makePersistent(photo);
		} finally {
			pm.close();
		}
	}

	/* (non-Javadoc)
	 * @see fspotcloud.server.model.photo.Photos#saveAll(java.util.List)
	 */
	public void saveAll(List<Photo> photoList) {
		PersistenceManager pm = pmProvider.get();
		try {
			pm.makePersistentAll(photoList);
		} finally {
			pm.close();
		}
	}

	/* (non-Javadoc)
	 * @see fspotcloud.server.model.photo.Photos#getById(java.lang.String)
	 */
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
