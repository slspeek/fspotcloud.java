package fspotcloud.client.admin.view;

import com.google.inject.Inject;

import fspotcloud.client.admin.ui.DashboardViewFactory;
import fspotcloud.client.admin.view.api.DashboardView;
import fspotcloud.client.main.view.api.TreeView;
import fspotcloud.client.main.view.api.TreeView.TreePresenter;

public class DashboardPresenter implements
		fspotcloud.client.admin.view.api.DashboardView.DashboardPresenter {
	
	private final DashboardViewFactory dashboardViewFactory;
	private final TreeView.TreePresenter treePresenter;
	private final GlobalActionsPresenter globalActionsPresenter;

	@Inject
	public DashboardPresenter(DashboardViewFactory dashboardViewFactory,
			TreePresenter treePresenter,
			GlobalActionsPresenter globalActionsPresenter) {
		super();
		this.dashboardViewFactory = dashboardViewFactory;
		this.treePresenter = treePresenter;
		this.globalActionsPresenter = globalActionsPresenter;
	}

	public DashboardView getView() {
		return dashboardViewFactory.get();
	}

	@Override
	public void init() {
		treePresenter.init();
		globalActionsPresenter.init();
	}

}
