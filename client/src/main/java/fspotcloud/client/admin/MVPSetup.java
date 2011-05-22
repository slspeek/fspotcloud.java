package fspotcloud.client.admin;

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

import fspotcloud.client.admin.view.MainWindowActivityMapper;
import fspotcloud.client.place.AppPlaceHistoryMapper;
import fspotcloud.client.place.TagViewingPlace;

public class MVPSetup {

	private static final Logger log = Logger
			.getLogger(MVPSetup.class.getName());
	final private Place defaultPlace = new TagViewingPlace("64", "8451");
	final private SimplePanel appWidget = new SimplePanel();
	final private EventBus eventBus;
	final private MainWindowActivityMapper activityMapper;
	final private PlaceController placeController;
	
	@Inject
	public MVPSetup(MainWindowActivityMapper activityMapper, EventBus eventBus,
			PlaceController placeController) {
		this.activityMapper = activityMapper;
		this.eventBus = eventBus;
		this.placeController = placeController;
	}

	public void setup() {
		log.info("Admin setup");
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
