package fspotcloud.client.view;

import com.google.gwt.place.shared.Place;

public class BasePlace extends Place {

	private String tagId;
	private String photoId;

	public BasePlace(String tagId, String photoId) {
		this.tagId = tagId;
		this.photoId = photoId;
	}

	public String getTagId() {
		return tagId;
	}

	public String getPhotoId() {
		return photoId;
	}
	
	public boolean equals(Object other) {
		if (other instanceof BasePlace) {
			BasePlace basePlace = (BasePlace) other;
			String tagId = basePlace.getTagId();
			String photoId = basePlace.getPhotoId();
			return equal(this.tagId, tagId) && equal(this.photoId, photoId);
		} else {
			return false;
		}
	}
	
	public static boolean equal(Object a, Object b) {
		if (a == null) {
			if (b== null) {
				return true;
			} else {
				return false;
			}
		} else {
			return a.equals(b);
		}
		
	}
}
