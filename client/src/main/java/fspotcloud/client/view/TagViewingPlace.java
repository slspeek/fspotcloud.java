package fspotcloud.client.view;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class TagViewingPlace extends Place {

	private String tagId;
	private String photoId;

	public TagViewingPlace(String tagId, String photoId) {
		if (tagId == null)
			throw new NullPointerException("Invalid TagPlace");
		this.tagId = tagId;
		this.photoId = photoId;
	}

	public String getTagId() {
		return tagId;
	}

	public String getPhotoId() {
		return photoId;
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

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof TagViewingPlace))
			return false;
		TagViewingPlace other = (TagViewingPlace) obj;
		return getTagId() != null && getTagId().equals(other.getTagId())
				&& getPhotoId() != null
				&& getPhotoId().equals(other.getPhotoId());
	}

	@Override
	public int hashCode() {
		if (getTagId() != null) {
			return getTagId().hashCode();
		} else {
			return 0;
		}
	}
}
