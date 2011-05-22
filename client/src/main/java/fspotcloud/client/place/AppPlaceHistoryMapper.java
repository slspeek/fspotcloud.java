package fspotcloud.client.place;

import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.google.gwt.place.shared.WithTokenizers;

import fspotcloud.client.place.TagViewingPlace.Tokenizer;

@WithTokenizers({TagViewingPlace.Tokenizer.class, ImageViewingPlace.Tokenizer.class})
public interface AppPlaceHistoryMapper extends PlaceHistoryMapper {

}
