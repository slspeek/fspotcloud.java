package fspotcloud.client.admin.view;

import com.google.inject.Inject;

import fspotcloud.client.admin.view.api.DashboardView;

public class DashboardPresenter implements
		fspotcloud.client.admin.view.api.DashboardView.DashboardPresenter {
	private final DashboardView dashboardView;

	@Inject
	public DashboardPresenter(DashboardView dashboardView) {
		super();
		this.dashboardView = dashboardView;
	}
	
	public DashboardView getView() {
		return dashboardView;
	}
	
}
