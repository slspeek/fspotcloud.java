package fspotcloud.client.place;

import com.google.gwt.place.shared.PlaceTokenizer;


public class SlideshowPlace extends BasePlace {
	
	
	public class Tokeninzer {

	}

	final private float interval;

	
	public SlideshowPlace(String tagId, String photoId, float interval) {
		super(tagId, photoId);
		this.interval = interval;
	}
	
	public float getInterval() {
		return interval;
	}

	public static class Tokenizer implements PlaceTokenizer<SlideshowPlace> {
		@Override
		public SlideshowPlace getPlace(String token) {
			TokenizerUtil util = new TokenizerUtil(token);
			return new SlideshowPlace(util.getTagId(), util.getPhotoId(), 0f);
		}

		@Override
		public String getToken(SlideshowPlace place) {
			return place.getTagId() + ":" + place.getPhotoId();
		}
	}
	
	public boolean equals(Object otherObject) {
		if (otherObject instanceof SlideshowPlace) {
			
			BasePlace basePlace = (BasePlace) otherObject;
			String tagId = basePlace.getTagId();
			String photoId = basePlace.getPhotoId();
			return equal(getTagId()
					, tagId) && equal(getPhotoId(), photoId);
		} else {
			return false;
		}
		
	}
	
	
}
