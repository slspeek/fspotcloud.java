package fspotcloud.client.view;

import com.google.gwt.place.shared.PlaceTokenizer;

public class ImageViewingPlace extends BasePlace {

	public ImageViewingPlace(String tagId, String photoId) {
		super(tagId, photoId);
	}

	
	@Override
	public boolean equals(Object other) {
		if (other instanceof ImageViewingPlace) {
			ImageViewingPlace otherPlace = (ImageViewingPlace) other;
			return super.equals(otherPlace);
		} else {
			return false;
		}
	}

	public static class Tokenizer implements PlaceTokenizer<ImageViewingPlace> {
		@Override
		public ImageViewingPlace getPlace(String token) {
			String[] tokens = token.split(":");
			return new ImageViewingPlace(tokens[0], tokens[1]);
		}

		@Override
		public String getToken(ImageViewingPlace place) {
			return place.getTagId() + ":" + place.getPhotoId();
		}
	}
}
