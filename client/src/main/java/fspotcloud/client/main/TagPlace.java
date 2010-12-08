package fspotcloud.client.main;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class TagPlace extends Place {

	private String tagId;
	
	public TagPlace(String tagId) {
		this.tagId = tagId;
	}

	public String getTagId() {
		return tagId;
	}
	public static class Tokenizer implements PlaceTokenizer<TagPlace> {
		@Override
		public TagPlace getPlace(String token) {
			return new TagPlace(token);
		}

		@Override
		public String getToken(TagPlace place) {
			return place.getTagId();
		}
	}
}
