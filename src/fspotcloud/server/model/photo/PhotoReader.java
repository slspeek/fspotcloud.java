package fspotcloud.server.model.photo;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import fspotcloud.server.util.PMF;

public class PhotoReader {
	@SuppressWarnings("unchecked")
	public List<String> getPhotoKeysForTag(String tagId) {
		List<String> keys = new ArrayList<String>();
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Query query = pm.newQuery(Photo.class);
		query.setFilter("tagList=" + tagId);
		query.setOrdering("date");
		List<Photo> result = (List<Photo>) query.execute();
		return keys;
	}
}
