package fspotcloud.client.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

import fspotcloud.client.place.api.ViewingPlace;

public class BasePlace extends Place implements ViewingPlace {

	final private String tagId;
	final private String photoId;
	final private int columnCount;
	final private int rowCount;
	final private boolean isTreeVisible;
	final private boolean buttonsVisible;

	public BasePlace(String tagId, String photoId) {
		this(tagId, photoId, 1, 1);
	}

	public BasePlace(String tagId, String photoId, int columnCount, int rowCount) {
		this(tagId, photoId, columnCount, rowCount, false, true);
	}

	public BasePlace(String tagId, String photoId, int columnCount,
			int rowCount, boolean treeVisible) {
		this(tagId, photoId, columnCount, rowCount, treeVisible, true);
	}

	public BasePlace(String tagId, String photoId, int columnCount,
			int rowCount, boolean treeVisible, boolean buttonsVisible) {
		this.tagId = tagId;
		this.photoId = photoId;
		this.columnCount = columnCount;
		this.rowCount = rowCount;
		this.isTreeVisible = treeVisible;
		this.buttonsVisible = buttonsVisible;
	}

	public String getTagId() {
		return tagId;
	}

	public String getPhotoId() {
		return photoId;
	}

	public int getColumnCount() {
		return columnCount;
	}

	public int getRowCount() {
		return rowCount;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		if (tagId != null)
			hash += tagId.hashCode();
		if (photoId != null)
			hash += photoId.hashCode();
		hash += Integer.valueOf(columnCount).hashCode();
		hash += Integer.valueOf(rowCount).hashCode();
		return hash;
	}

	@Override
	public boolean equals(Object other) {
		if (other instanceof BasePlace) {
			BasePlace basePlace = (BasePlace) other;
			String tagId = basePlace.getTagId();
			String photoId = basePlace.getPhotoId();
			int columnCount = basePlace.getColumnCount();
			int rowCount = basePlace.getRowCount();
			boolean isTreeVisible = basePlace.hasTreeVisible();
			boolean hasButtons = basePlace.hasButtonsVisible();
			return equal(this.tagId, tagId) && equal(this.photoId, photoId)
					&& equal(this.rowCount, rowCount)
					&& equal(this.columnCount, columnCount)
					&& isTreeVisible == hasTreeVisible()
					&& hasButtons == hasButtonsVisible();
		} else {
			return false;
		}
	}

	public String toString() {
		String result = "<<Place tagId: " + tagId + " photoId: " + photoId
				+ "(" + columnCount + "x" + rowCount + ")  tree:"
				+ isTreeVisible + " buttons:" + buttonsVisible + ">>";
		return result;
	}

	public static boolean equal(Object a, Object b) {
		if (a == null) {
			if (b == null) {
				return true;
			} else {
				return false;
			}
		} else {
			return a.equals(b);
		}

	}

	public boolean hasTreeVisible() {
		return isTreeVisible;
	}

	public static class Tokenizer implements PlaceTokenizer<BasePlace> {
		@Override
		public BasePlace getPlace(String token) {
			TokenizerUtil util = new TokenizerUtil(token);
			return new BasePlace(util.getTagId(), util.getPhotoId(),
					util.getColumnCount(), util.getRowCount(),
					util.isTreeVisible(), util.isButtonsVisible());
		}

		@Override
		public String getToken(BasePlace place) {
			return place.getTagId() + ":" + place.getPhotoId() + ":"
					+ place.getColumnCount() + ":" + place.getRowCount() + ":"
					+ place.hasTreeVisible() + ":" + place.hasButtonsVisible();
		}
	}

	@Override
	public boolean hasButtonsVisible() {
		return buttonsVisible;
	}
}
