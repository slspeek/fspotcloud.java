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

import fspotcloud.client.view.MainWindowActivityMapper;
import fspotcloud.client.view.AppPlaceHistoryMapper;
import fspotcloud.client.view.TagView;
import fspotcloud.client.view.TagViewingPlace;

public class MVPSetup {

	private static final Logger log = Logger.getLogger(MVPSetup.class
			.getName());
	final private Place defaultPlace = new TagViewingPlace("1", "2");
	final private SimplePanel appWidget = new SimplePanel();
	final private EventBus eventBus;
	final private MainWindowActivityMapper activityMapper;
	final private PlaceController placeController;
	final private GlobalShortcutController keyboardHandler;

	@Inject
	public MVPSetup(MainWindowActivityMapper activityMapper, EventBus eventBus,
			PlaceController placeController,
			GlobalShortcutController keyboardHandler) {
		this.activityMapper = activityMapper;
		this.eventBus = eventBus;
		this.placeController = placeController;
		this.keyboardHandler = keyboardHandler;
	}

	public void setup() {
		keyboardHandler.setup();
		ActivityManager activityManager = new ActivityManager(activityMapper,
				eventBus);
		activityManager.setDisplay(appWidget);

		AppPlaceHistoryMapper historyMapper = GWT
				.create(AppPlaceHistoryMapper.class);
		PlaceHistoryHandler historyHandler = new PlaceHistoryHandler(
				historyMapper);
		historyHandler.register(placeController, eventBus, defaultPlace);

		RootLayoutPanel.get().add(appWidget);
		log.info("Just before handleCurrentHistory()");
		historyHandler.handleCurrentHistory();
		log.info("Setup finished");
	}
}
