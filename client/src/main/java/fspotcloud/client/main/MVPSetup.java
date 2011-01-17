package fspotcloud.client.main;

import com.google.gwt.activity.shared.ActivityManager;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.place.shared.PlaceHistoryHandler;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.inject.Inject;

public class MVPSetup {

	final private TagPlace defaultPlace = new TagPlace("1", "1");
	final private SimplePanel appWidget = new SimplePanel();
	final private EventBus eventBus;
	final private AppActivityMapper activityMapper;

	@Inject
	public MVPSetup(AppActivityMapper activityMapper, EventBus eventBus){
		this.activityMapper = activityMapper;
		this.eventBus = eventBus;
	}
	
	public void setup() {
		PlaceController placeController = new PlaceController(eventBus);
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
