package fspotcloud.client.main;

import java.util.logging.Logger;

import com.google.gwt.activity.shared.ActivityManager;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.place.shared.PlaceHistoryHandler;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.inject.Inject;

import fspotcloud.client.main.api.Initializable;
import fspotcloud.client.main.ui.HasOneWidgetAdapter;
import fspotcloud.client.main.ui.Resources;
import fspotcloud.client.main.view.MainWindowActivityMapper;
import fspotcloud.client.place.BasePlace;
import fspotcloud.client.place.MainPlaceHistoryMapper;

public class MVPSetup {

    private static final Logger log = Logger.getLogger(MVPSetup.class.getName());
    final private Place defaultPlace = new BasePlace("latest", "latest", 1, 1);
    final private DockLayoutPanel appWidget = new DockLayoutPanel(Unit.PX);
    final private EventBus eventBus;
    final private MainWindowActivityMapper activityMapper;
    final private PlaceController placeController;
    final private Initializable keyboardHandler;
    final private EventHandlersSetup eventSetup;
    private final UserInformation userInformation;

    @Inject
    public MVPSetup(MainWindowActivityMapper activityMapper, EventBus eventBus,
            PlaceController placeController,
            IGlobalShortcutController keyboardHandler,
            EventHandlersSetup eventSetup, Resources resources, UserInformation userInformation) {
        this.activityMapper = activityMapper;
        this.eventBus = eventBus;
        this.placeController = placeController;
        this.keyboardHandler = keyboardHandler;
        this.eventSetup = eventSetup;
        this.userInformation = userInformation;
        resources.style().ensureInjected();
    }

    public void setup() {
        keyboardHandler.init();
        log.info("Starting MVP setup");
        eventSetup.setUp();
        ActivityManager activityManager = new ActivityManager(activityMapper,
                eventBus);
        activityManager.setDisplay(new HasOneWidgetAdapter(appWidget));

        MainPlaceHistoryMapper historyMapper = GWT.create(MainPlaceHistoryMapper.class);
        PlaceHistoryHandler historyHandler = new PlaceHistoryHandler(
                historyMapper);
        historyHandler.register(placeController, eventBus, defaultPlace);

        log.info("Just before handleCurrentHistory()");
        RootLayoutPanel.get().add(appWidget);
        historyHandler.handleCurrentHistory();
        log.info("Setup finished");
        userInformation.getAsync();
    }
}
