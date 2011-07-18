package fspotcloud.client.main;

import java.util.logging.Logger;

import com.google.gwt.activity.shared.ActivityManager;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.place.shared.PlaceHistoryHandler;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.inject.Inject;

import fspotcloud.client.main.view.MainWindowActivityMapper;
import fspotcloud.client.place.BasePlace;
import fspotcloud.client.place.MainPlaceHistoryMapper;

public class MVPSetup {

	private static final Logger log = Logger
			.getLogger(MVPSetup.class.getName());
	final private Place defaultPlace = new BasePlace("latest", "latest", 1, 1, true);
	final private SimplePanel appWidget = new SimplePanel();
	final private EventBus eventBus;
	final private MainWindowActivityMapper activityMapper;
	final private PlaceController placeController;
	final private GlobalShortcutController keyboardHandler;
	final private EventHandlersSetup eventSetup;
	@Inject
	public MVPSetup(MainWindowActivityMapper activityMapper, EventBus eventBus,
			PlaceController placeController,
			GlobalShortcutController keyboardHandler,
			EventHandlersSetup eventSetup
			) {
		this.activityMapper = activityMapper;
		this.eventBus = eventBus;
		this.placeController = placeController;
		this.keyboardHandler = keyboardHandler;
		this.eventSetup = eventSetup;
	}

	public void setup() {
		
		keyboardHandler.setup();
		log.info("New event setup");
		eventSetup.setUp();
		ActivityManager activityManager = new ActivityManager(activityMapper,
				eventBus);
		activityManager.setDisplay(appWidget);

		MainPlaceHistoryMapper historyMapper = GWT
				.create(MainPlaceHistoryMapper.class);
		PlaceHistoryHandler historyHandler = new PlaceHistoryHandler(
				historyMapper);
		historyHandler.register(placeController, eventBus, defaultPlace);

		RootLayoutPanel.get().add(appWidget);
		
		log.info("Just before handleCurrentHistory()");
		historyHandler.handleCurrentHistory();
		log.info("Setup finished");
	}
}
