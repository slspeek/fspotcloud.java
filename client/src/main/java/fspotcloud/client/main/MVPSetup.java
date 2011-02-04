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

import fspotcloud.client.view.AppActivityMapper;
import fspotcloud.client.view.AppPlaceHistoryMapper;
import fspotcloud.client.view.TagActivity;
import fspotcloud.client.view.TagView;
import fspotcloud.client.view.TagViewingPlace;

public class MVPSetup {

	final private Place defaultPlace = new TagViewingPlace("1", "2");
	final private SimplePanel appWidget = new SimplePanel();
	final private EventBus eventBus;
	final private AppActivityMapper activityMapper;
	final private PlaceController placeController;
	final private GlobalShortcutController keyboardHandler;
	final private TagView.TagPresenter tagActivity;

	@Inject
	public MVPSetup(AppActivityMapper activityMapper, EventBus eventBus,
			PlaceController placeController,
			GlobalShortcutController keyboardHandler,
			TagView.TagPresenter tagActivity) {
		this.activityMapper = activityMapper;
		this.eventBus = eventBus;
		this.placeController = placeController;
		this.keyboardHandler = keyboardHandler;
		this.tagActivity = tagActivity;
	}

	public void setup() {
		tagActivity.reloadTree();
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
		historyHandler.handleCurrentHistory();
	}
}
