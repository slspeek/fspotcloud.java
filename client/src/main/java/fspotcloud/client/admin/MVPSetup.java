package fspotcloud.client.admin;

import java.util.logging.Logger;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.place.shared.PlaceHistoryHandler;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

import fspotcloud.client.admin.view.DashboardPresenter;
import fspotcloud.client.place.AdminPlaceHistoryMapper;
import fspotcloud.client.place.TagPlace;

public class MVPSetup {

	private static final Logger log = Logger
			.getLogger(MVPSetup.class.getName());
	final private Place defaultPlace = new TagPlace("1");
	final private EventBus eventBus;
	final private PlaceController placeController;
	final private DashboardPresenter dashboard;
	
	@Inject
	public MVPSetup(EventBus eventBus, PlaceController placeController,
			DashboardPresenter dashboard) {
		super();
		this.eventBus = eventBus;
		this.placeController = placeController;
		this.dashboard = dashboard;
	}

	public void setup() {
		log.info("Admin setup");
		AdminPlaceHistoryMapper historyMapper = GWT
				.create(AdminPlaceHistoryMapper.class);
		PlaceHistoryHandler historyHandler = new PlaceHistoryHandler(
				historyMapper);
		historyHandler.register(placeController, eventBus, defaultPlace);

		log.info("Just before handleCurrentHistory()");
		historyHandler.handleCurrentHistory();

		dashboard.init();
		Widget w = dashboard.getView().asWidget();
		RootLayoutPanel.get().add(w);
		log.info("Setup finished");
	}
}
