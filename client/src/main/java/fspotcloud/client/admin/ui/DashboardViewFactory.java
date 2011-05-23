package fspotcloud.client.admin.ui;

import java.util.logging.Logger;

import com.google.gwt.event.shared.EventBus;
import com.google.inject.Inject;

import fspotcloud.client.admin.view.TagDetailsActivityMapper;
import fspotcloud.client.admin.view.api.DashboardView;
import fspotcloud.client.main.ui.ViewFactory;

public class DashboardViewFactory extends ViewFactory {
	private static final Logger log = Logger.getLogger(DashboardViewFactory.class
			.getName());
	final private DashboardView dashboardView;

	@Inject
	public DashboardViewFactory(EventBus eventBus, DashboardView dashboardView,
			TagDetailsActivityMapper detailsMapper) {
		super(eventBus);
		this.dashboardView = dashboardView;
		register(detailsMapper, dashboardView.getTagDetailsContainer());
		log.info("after register");
	}

	public DashboardView get() {
		return dashboardView;
	}

}
