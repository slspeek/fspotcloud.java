package com.googlecode.fspotcloud.client.place;

import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.google.gwt.place.shared.WithTokenizers;

@WithTokenizers({BasePlace.Tokenizer.class, SlideshowPlace.Tokenizer.class})
public interface MainPlaceHistoryMapper extends PlaceHistoryMapper {

}