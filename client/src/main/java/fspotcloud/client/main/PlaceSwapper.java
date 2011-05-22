package fspotcloud.client.main;

import com.google.gwt.place.shared.Place;

import fspotcloud.client.place.ImageViewingPlace;
import fspotcloud.client.place.TagViewingPlace;

public class PlaceSwapper {

	public Place swap(Place place) {
		Place result = null;
		if (place instanceof TagViewingPlace && !(place instanceof ImageViewingPlace)) {
			TagViewingPlace tagPlace = (TagViewingPlace) place;
			ImageViewingPlace imagePlace = new ImageViewingPlace(
					tagPlace.getTagId(), tagPlace.getPhotoId());
			result = imagePlace;
		} else if (place instanceof ImageViewingPlace) {
			ImageViewingPlace imagePlace = (ImageViewingPlace) place;
			TagViewingPlace tagPlace = new TagViewingPlace(imagePlace
					.getTagId(), imagePlace.getPhotoId());
			result = tagPlace;
		} else {
			throw new IllegalStateException();
		}
		return result;
	}
}
