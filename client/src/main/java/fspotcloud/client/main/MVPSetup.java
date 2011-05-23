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
import fspotcloud.client.place.MainPlaceHistoryMapper;
import fspotcloud.client.place.TagViewingPlace;
import fspotcloud.client.view.action.NavigationEventHandler;
import fspotcloud.client.view.action.SlideshowEventHandler;

public class MVPSetup {

	private static final Logger log = Logger
			.getLogger(MVPSetup.class.getName());
	final private Place defaultPlace = new TagViewingPlace("64", "8451");
	final private SimplePanel appWidget = new SimplePanel();
	final private EventBus eventBus;
	final private MainWindowActivityMapper activityMapper;
	final private PlaceController placeController;
	final private GlobalShortcutController keyboardHandler;
	final private SlideshowEventHandler slideshowEventHandler;
	final private NavigationEventHandler navigationEventHandler;

	@Inject
	public MVPSetup(MainWindowActivityMapper activityMapper, EventBus eventBus,
			PlaceController placeController,
			GlobalShortcutController keyboardHandler,
			SlideshowEventHandler slideshowEventHandler,
			NavigationEventHandler navigationEventHandler) {
		this.activityMapper = activityMapper;
		this.eventBus = eventBus;
		this.placeController = placeController;
		this.keyboardHandler = keyboardHandler;
		this.slideshowEventHandler = slideshowEventHandler;
		this.navigationEventHandler = navigationEventHandler;
	}

	public void setup() {
		keyboardHandler.setup();
		slideshowEventHandler.init();
		navigationEventHandler.init();
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
