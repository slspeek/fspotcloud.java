package fspotcloud.client.main;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.place.shared.PlaceController;

import fspotcloud.client.main.ui.TagViewImpl;
import fspotcloud.rpc.TagService;
import fspotcloud.rpc.TagServiceAsync;

public class ClientFactoryImpl implements ClientFactory {
	private final EventBus eventBus = new SimpleEventBus();
	private final PlaceController placeController = new PlaceController(
			eventBus);
	private final TagView tagView = new TagViewImpl();
	private final TagServiceAsync tagService = GWT.create(TagService.class);
	private final DataManager dataManager = new DataManager(tagService);

	@Override
	public EventBus getEventBus() {
		return eventBus;
	}

	@Override
	public PlaceController getPlaceController() {
		return placeController;
	}

	@Override
	public TagView getTagView() {
		return tagView;
	}

	@Override
	public TagServiceAsync getTagService() {
		return tagService;
	}

	@Override
	public DataManager getDataManager() {
		return dataManager;
	}
	
}
