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
import com.google.inject.name.Named;

public class PhotoManager {
	
	private final int STEP;
	
	private static final Logger log = Logger.getLogger(PhotoManager.class
			.getName());
	
	private final PersistenceManager pm;
	
	@Inject
	public PhotoManager(PersistenceManager pm, @Named("maxDelete") int maxDelete) {
		this.pm = pm;
		this.STEP = maxDelete;
	}
	
	@SuppressWarnings("unchecked")
	public List<String> getPhotoKeysForTag(String tagId) {
		log.warning("TagId: " + tagId);
		List<String> keys = new ArrayList<String>();
		Query query = pm.newQuery(Photo.class);
		query.setFilter("tagList == paramTag");
		query.declareParameters("String paramTag");// + " && imageLoaded==True");
		query.setOrdering("date");
		query.setRange(0, 30);
		List<Photo> result = (List<Photo>) query.execute(tagId);
		for(Photo photo: result) {
			keys.add(photo.getName());
		}
		return keys;
	}
	
	@SuppressWarnings("unchecked")
	public List<Photo> getPhotosStartingAtDate(Date minDate, int limit) {
		Query query = pm.newQuery(Photo.class);
		query.setFilter(" date > dateParam");
		query.declareImports("import java.util.Date");
		query.declareParameters("Date dateParam");
		query.setOrdering("date");
		query.setRange(0, limit);
		List<Photo> result = (List<Photo>) query.execute(minDate);
		return result;
	}
	
	public Photo getOrNew(String id) {
		Photo photo = null;
		try {
			photo = pm.getObjectById(Photo.class, id);
		} catch (JDOObjectNotFoundException notExistedYet) {
			photo = new Photo();
			photo.setName(id);
		}
		return photo;
	}
	
	@SuppressWarnings("unchecked")
	public List<Photo> getOldestPhotosChunk() {
		Query query = pm.newQuery(Photo.class);
		query.setOrdering("date");
		query.setRange(0, STEP);
		List<Photo> result = (List<Photo>) query.execute();
		return result;
	}
	
	public void delete(Photo p) {
		pm.deletePersistent(p);
	}
	
	public void deleteAll(Collection photos) {
		pm.deletePersistentAll(photos);
	}

	public void save(Photo photo) {
		pm.makePersistent(photo);
	}

	public void saveAll(List<Photo> photoList) {
		pm.makePersistentAll(photoList);
		
	}
	
}
