package fspotcloud.client.place;

import com.google.gwt.place.shared.PlaceTokenizer;



public class TagViewingPlace extends BasePlace {

	public TagViewingPlace(String tagId, String photoId) {
		super(tagId, photoId);
	}

	
	@Override
	public boolean equals(Object other) {
		if (other instanceof TagViewingPlace) {
			TagViewingPlace otherPlace = (TagViewingPlace) other;
			return super.equals(otherPlace);
		} else {
			return false;
		}
		
	}


	public static class Tokenizer implements PlaceTokenizer<TagViewingPlace> {
		@Override
		public TagViewingPlace getPlace(String token) {
			String[] tokens = token.split(":");
			return new TagViewingPlace(tokens[0], tokens[1]);
		}

		@Override
		public String getToken(TagViewingPlace place) {
			return place.getTagId() + ":" + place.getPhotoId();
		}
	}
}
