package fspotcloud.client.main;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class ImagePlace extends Place {

	private String tagId;
	private String photoId;

	public ImagePlace(String tagId, String photoId) {
		if (tagId == null)
			throw new NullPointerException("Invalid ImagePlace");
		this.tagId = tagId;
		this.photoId = photoId;
	}

	public String getTagId() {
		return tagId;
	}

	public String getPhotoId() {
		return photoId;
	}

	public static class Tokenizer implements PlaceTokenizer<ImagePlace> {
		@Override
		public ImagePlace getPlace(String token) {
			String[] tokens = token.split(":");
			return new ImagePlace(tokens[0], tokens[1]);
		}

		@Override
		public String getToken(ImagePlace place) {
			return place.getTagId() + ":" + place.getPhotoId();
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof ImagePlace))
			return false;
		ImagePlace other = (ImagePlace) obj;
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
