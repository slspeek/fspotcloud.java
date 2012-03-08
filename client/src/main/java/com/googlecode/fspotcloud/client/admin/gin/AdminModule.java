package com.googlecode.fspotcloud.client.admin.gin;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.inject.client.AbstractGinModule;
import com.google.gwt.place.shared.PlaceController;
import com.google.inject.Singleton;

import com.googlecode.fspotcloud.client.admin.ui.DashboardViewImpl;
import com.googlecode.fspotcloud.client.admin.ui.GlobalActionsViewImpl;
import com.googlecode.fspotcloud.client.admin.ui.TagDetailsViewImpl;
import com.googlecode.fspotcloud.client.admin.view.AdminTreePresenterImpl;
import com.googlecode.fspotcloud.client.admin.view.TagDetailsActivityMapper;
import com.googlecode.fspotcloud.client.admin.view.api.DashboardView;
import com.googlecode.fspotcloud.client.admin.view.api.GlobalActionsView;
import com.googlecode.fspotcloud.client.admin.view.api.TagDetailsActivityFactory;
import com.googlecode.fspotcloud.client.admin.view.api.TagDetailsView;
import com.googlecode.fspotcloud.client.data.DataManager;
import com.googlecode.fspotcloud.client.data.DataManagerImpl;
import com.googlecode.fspotcloud.client.main.gin.PlaceControllerProvider;
import com.googlecode.fspotcloud.client.main.ui.TimerImpl;
import com.googlecode.fspotcloud.client.main.ui.TreeViewImpl;
import com.googlecode.fspotcloud.client.main.view.TagCell;
import com.googlecode.fspotcloud.client.main.view.api.TimerInterface;
import com.googlecode.fspotcloud.client.main.view.api.TreeView;
import com.googlecode.fspotcloud.client.place.PlaceGoToImpl;
import com.googlecode.fspotcloud.client.place.api.PlaceGoTo;


public class AdminModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(TagDetailsActivityMapper.class).in(Singleton.class);
		bind(TagDetailsActivityFactory.class).to(TagDetailsActivityFactoryImpl.class);
		bind(TagDetailsView.class).to(TagDetailsViewImpl.class).in(Singleton.class);
		bind(DashboardView.class).to(DashboardViewImpl.class).in(Singleton.class);
		bind(GlobalActionsView.class).to(GlobalActionsViewImpl.class).in(Singleton.class);
		bind(DataManager.class).to(DataManagerImpl.class).in(Singleton.class);
		bind(TagCell.class);
		bind(PlaceGoTo.class).to(PlaceGoToImpl.class);
		bind(PlaceController.class).toProvider(PlaceControllerProvider.class);
		bind(PlaceControllerProvider.class).in(Singleton.class);
		bind(EventBus.class).to(SimpleEventBus.class).in(Singleton.class);
		bind(TreeView.TreePresenter.class).to(AdminTreePresenterImpl.class).in(
				Singleton.class);
		bind(TreeView.class).to(TreeViewImpl.class).in(Singleton.class);
		bind(TimerInterface.class).to(TimerImpl.class);
	}
}
