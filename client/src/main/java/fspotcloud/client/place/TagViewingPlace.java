package fspotcloud.client.place;

import com.google.gwt.place.shared.PlaceTokenizer;

public class TagViewingPlace extends BasePlace {

	public TagViewingPlace(String tagId, String photoId) {
		super(tagId, photoId);
	}

	public TagViewingPlace(String tagId, String photoId, int columnCount,
			int rowCount) {
		super(tagId, photoId, columnCount, rowCount);
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
			TokenizerUtil util = new TokenizerUtil(token);
			return new TagViewingPlace(util.getTagId(), util.getPhotoId(),
					util.getColumnCount(), util.getRowCount());
		}

		@Override
		public String getToken(TagViewingPlace place) {
			return place.getTagId() + ":" + place.getPhotoId() + ":"
					+ place.getColumnCount() + ":" + place.getRowCount();
		}
	}
}
