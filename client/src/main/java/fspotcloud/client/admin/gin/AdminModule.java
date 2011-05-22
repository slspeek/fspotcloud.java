package fspotcloud.client.admin.gin;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.inject.client.AbstractGinModule;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.inject.Singleton;

import fspotcloud.client.admin.MVPSetup;
import fspotcloud.client.admin.view.MainWindowActivityMapper;
import fspotcloud.client.data.DataManager;
import fspotcloud.client.data.DataManagerImpl;
import fspotcloud.client.main.gin.PlaceControllerProvider;
import fspotcloud.client.main.ui.TreeViewImpl;
import fspotcloud.client.main.view.TagCell;
import fspotcloud.client.main.view.TreePresenterImpl;
import fspotcloud.client.main.view.TreeSelectionHandler;
import fspotcloud.client.main.view.api.TreeView;
import fspotcloud.client.place.PlaceGoTo;
import fspotcloud.client.place.PlaceGoToImpl;


public class AdminModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(MainWindowActivityMapper.class).in(Singleton.class);
		bind(DataManager.class).to(DataManagerImpl.class).in(Singleton.class);
		bind(MVPSetup.class).in(Singleton.class);
		bind(TagCell.class);
		bind(PlaceGoTo.class).to(PlaceGoToImpl.class);
		bind(PlaceController.class).toProvider(PlaceControllerProvider.class);
		bind(PlaceControllerProvider.class).in(Singleton.class);
		bind(EventBus.class).to(SimpleEventBus.class).in(Singleton.class);
		bind(TreeView.TreePresenter.class).to(TreePresenterImpl.class).in(
				Singleton.class);
		bind(SelectionChangeEvent.Handler.class).to(TreeSelectionHandler.class)
				.in(Singleton.class);
		bind(TreeView.class).to(TreeViewImpl.class).in(Singleton.class);
	}
}
