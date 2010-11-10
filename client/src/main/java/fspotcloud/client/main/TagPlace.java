package fspotcloud.client.main;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class TagPlace extends Place {

	private String tagId;
	private String photoId;

	public TagPlace(String tagId, String photoId) {
		this.tagId = tagId;
		this.photoId = photoId;
	}

	public TagPlace(String tagId) {
		this(tagId, null);
	}

	public String getTagId() {
		return tagId;
	}

	public String getPhotoId() {
		return photoId;
	}

	public static class Tokenizer implements PlaceTokenizer<TagPlace> {

		@Override
		public TagPlace getPlace(String token) {
			String[] tokens = token.split(":");
			if (tokens == null) {
				return new TagPlace(token);
			} else {
				return new TagPlace(tokens[0], tokens[1]);
			}
		}

		@Override
		public String getToken(TagPlace place) {
			if (place.getPhotoId() == null) {
				return null;
			} else {
				return place.getTagId() + ":" + place.getPhotoId();
			}
		}

	}
}
