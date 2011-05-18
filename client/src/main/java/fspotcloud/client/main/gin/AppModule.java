package fspotcloud.client.main.gin;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.inject.client.AbstractGinModule;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.inject.Singleton;

import fspotcloud.client.data.DataManager;
import fspotcloud.client.data.DataManagerImpl;
import fspotcloud.client.main.MVPSetup;
import fspotcloud.client.main.Navigator;
import fspotcloud.client.main.NavigatorImpl;
import fspotcloud.client.main.PlaceSwapper;
import fspotcloud.client.main.Slideshow;
import fspotcloud.client.main.SlideshowImpl;
import fspotcloud.client.main.TagCell;
import fspotcloud.client.main.ui.ImagePanelViewImpl;
import fspotcloud.client.main.ui.ImageViewImpl;
import fspotcloud.client.main.ui.PagerViewImpl;
import fspotcloud.client.main.ui.SlideshowTimer;
import fspotcloud.client.main.ui.SlideshowViewImpl;
import fspotcloud.client.main.ui.TagViewImpl;
import fspotcloud.client.main.ui.TreeViewImpl;
import fspotcloud.client.view.ImagePanelActivityFactory;
import fspotcloud.client.view.ImagePanelActivityFactoryImpl;
import fspotcloud.client.view.ImagePanelView;
import fspotcloud.client.view.ImageView;
import fspotcloud.client.view.MainWindowActivityMapper;
import fspotcloud.client.view.PagerView;
import fspotcloud.client.view.PlaceGoTo;
import fspotcloud.client.view.PlaceGoToImpl;
import fspotcloud.client.view.PlaceWhere;
import fspotcloud.client.view.PlaceWhereImpl;
import fspotcloud.client.view.SlideShowPresenterImpl;
import fspotcloud.client.view.SlideshowView;
import fspotcloud.client.view.TagPresenterFactory;
import fspotcloud.client.view.TagPresenterFactoryImpl;
import fspotcloud.client.view.TagView;
import fspotcloud.client.view.TimerInterface;
import fspotcloud.client.view.TreePresenterImpl;
import fspotcloud.client.view.TreeSelectionHandler;
import fspotcloud.client.view.TreeView;
import fspotcloud.client.view.action.KeyDispatcherProvider;
import fspotcloud.client.view.action.ShortCutHandler;

public class AppModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(MainWindowActivityMapper.class).in(Singleton.class);
		bind(DataManager.class).to(DataManagerImpl.class).in(Singleton.class);
		bind(MVPSetup.class).in(Singleton.class);
		bind(PlaceSwapper.class);
		bind(TagCell.class);
		bind(TagView.class).to(TagViewImpl.class);
		bind(ImageView.class).to(ImageViewImpl.class);
		bind(ImagePanelView.class).to(ImagePanelViewImpl.class);
		bind(PlaceGoTo.class).to(PlaceGoToImpl.class);
		bind(PlaceWhere.class).to(PlaceWhereImpl.class);
		bind(PlaceController.class).toProvider(PlaceControllerProvider.class);
		bind(PlaceControllerProvider.class).in(Singleton.class);
		bind(EventBus.class).to(SimpleEventBus.class).in(Singleton.class);
		bind(KeyDispatcherProvider.class).in(Singleton.class);
		bind(ShortCutHandler.class).toProvider(KeyDispatcherProvider.class);
		bind(SlideshowView.class).to(SlideshowViewImpl.class);
		bind(SlideshowView.SlideshowPresenter.class).to(
				SlideShowPresenterImpl.class);
		bind(PagerView.class).to(PagerViewImpl.class);
		bind(TimerInterface.class).to(SlideshowTimer.class);
		bind(TagPresenterFactory.class).to(TagPresenterFactoryImpl.class);
		bind(TreeView.TreePresenter.class).to(TreePresenterImpl.class).in(
				Singleton.class);
		bind(SelectionChangeEvent.Handler.class).to(TreeSelectionHandler.class)
				.in(Singleton.class);
		bind(TreeView.class).to(TreeViewImpl.class).in(Singleton.class);
		bind(ImagePanelActivityFactory.class).to(
				ImagePanelActivityFactoryImpl.class);
		bind(Navigator.class).to(NavigatorImpl.class).in(Singleton.class);
		bind(Slideshow.class).to(SlideshowImpl.class).in(Singleton.class);
	}
}
