package fspotcloud.client.view;

import com.google.gwt.place.shared.PlaceTokenizer;

public class ImageViewingPlace extends TagViewingPlace {

	public ImageViewingPlace(String tagId, String photoId) {
		super(tagId, photoId);
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
