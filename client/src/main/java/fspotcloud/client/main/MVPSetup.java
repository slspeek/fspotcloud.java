package fspotcloud.client.main;

import com.google.gwt.activity.shared.ActivityManager;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.place.shared.PlaceHistoryHandler;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.inject.Inject;

public class MVPSetup {

	final private Place defaultPlace = new TagPlace("1", "5");
	final private SimplePanel appWidget = new SimplePanel();
	final private EventBus eventBus;
	final private AppActivityMapper activityMapper;
	final private PlaceController placeController;

	@Inject
	public MVPSetup(AppActivityMapper activityMapper, EventBus eventBus,
			PlaceController placeController) {
		this.activityMapper = activityMapper;
		this.eventBus = eventBus;
		this.placeController = placeController;
	}

	public void setup() {
		ActivityManager activityManager = new ActivityManager(activityMapper,
				eventBus);
		activityManager.setDisplay(appWidget);

		AppPlaceHistoryMapper historyMapper = GWT
				.create(AppPlaceHistoryMapper.class);
		PlaceHistoryHandler historyHandler = new PlaceHistoryHandler(
				historyMapper);
		historyHandler.register(placeController, eventBus, defaultPlace);

		RootLayoutPanel.get().add(appWidget);
		historyHandler.handleCurrentHistory();
	}
}
