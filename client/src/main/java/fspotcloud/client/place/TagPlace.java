package fspotcloud.client.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class TagPlace extends Place {

	public static class Tokenizer implements PlaceTokenizer<TagPlace> {
		@Override
		public TagPlace getPlace(String token) {
			return new TagPlace(token);
		}

		@Override
		public String getToken(TagPlace place) {
			return place.getTagId() ;
		}
	}
	
	private String tagId;

	public TagPlace(String tagId) {
		this.tagId = tagId;
	}

	public String getTagId() {
		return tagId;
	}

	@Override
	public boolean equals(Object other) {
		if (other instanceof TagPlace) {
			TagPlace basePlace = (TagPlace) other;
			String tagId = basePlace.getTagId();
			return equal(this.tagId, tagId);
		} else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		int hash = 0;
		if (tagId != null)
			hash += tagId.hashCode();
		return hash;
	}

	public String toString() {
		String result = getClass().getName() + ": tagId: " + tagId;
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
}
