package fspotcloud.client.main;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.PlaceController;

import fspotcloud.rpc.TagServiceAsync;

public interface ClientFactory {
	EventBus getEventBus();
	
	PlaceController getPlaceController();
	
	TagView getTagView();
	
	DataManager getDataManager();
	
	TagServiceAsync getTagService();
}
