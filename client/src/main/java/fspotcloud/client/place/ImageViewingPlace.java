package fspotcloud.client.place;

import com.google.gwt.place.shared.PlaceTokenizer;

public class ImageViewingPlace extends BasePlace {

	public ImageViewingPlace(String tagId, String photoId) {
		super(tagId, photoId);
	}

	public ImageViewingPlace(String tagId, String photoId, int columnCount,
			int rowCount) {
		super(tagId, photoId, columnCount, rowCount);
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
			TokenizerUtil util = new TokenizerUtil(token);
			return new ImageViewingPlace(util.getTagId(), util.getPhotoId(),
					util.getColumnCount(), util.getRowCount());
		}

		@Override
		public String getToken(ImageViewingPlace place) {
			return place.getTagId() + ":" + place.getPhotoId() + ":"
					+ place.getColumnCount() + ":" + place.getRowCount();
		}
	}
}
