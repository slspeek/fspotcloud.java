package fspotcloud.client.place;

import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.google.gwt.place.shared.WithTokenizers;

import fspotcloud.client.place.TagViewingPlace.Tokenizer;

@WithTokenizers({TagPlace.Tokenizer.class})
public interface AdminPlaceHistoryMapper extends PlaceHistoryMapper {

}
