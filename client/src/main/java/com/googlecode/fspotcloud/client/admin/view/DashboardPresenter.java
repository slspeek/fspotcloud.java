package com.googlecode.fspotcloud.client.admin.view;

import com.google.inject.Inject;

import com.googlecode.fspotcloud.client.admin.ui.DashboardViewFactory;
import com.googlecode.fspotcloud.client.admin.view.api.DashboardView;
import com.googlecode.fspotcloud.client.main.view.api.TreeView;
import com.googlecode.fspotcloud.client.main.view.api.TreeView.TreePresenter;

public class DashboardPresenter implements
		com.googlecode.fspotcloud.client.admin.view.api.DashboardView.DashboardPresenter {
	
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
