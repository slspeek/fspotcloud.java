package fspotcloud.client.main.gin;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.inject.client.AbstractGinModule;
import com.google.gwt.place.shared.PlaceController;
import com.google.inject.Singleton;
import com.google.inject.name.Names;

import fspotcloud.client.data.DataManager;
import fspotcloud.client.data.DataManagerImpl;
import fspotcloud.client.main.MVPSetup;
import fspotcloud.client.main.PlaceSwapper;
import fspotcloud.client.main.TagCell;
import fspotcloud.client.main.ui.ImageViewImpl;
import fspotcloud.client.main.ui.TagViewImpl;
import fspotcloud.client.view.AppActivityMapper;
import fspotcloud.client.view.EmbeddedImageActivity;
import fspotcloud.client.view.ImageActivity;
import fspotcloud.client.view.ImageView;
import fspotcloud.client.view.PlaceGoTo;
import fspotcloud.client.view.PlaceGoToImpl;
import fspotcloud.client.view.PlaceWhere;
import fspotcloud.client.view.PlaceWhereImpl;
import fspotcloud.client.view.Slideshow;
import fspotcloud.client.view.SlideshowTimer;
import fspotcloud.client.view.TagActivity;
import fspotcloud.client.view.TagView;
import fspotcloud.client.view.action.KeyDispatcherProvider;
import fspotcloud.client.view.action.ShortCutHandler;

public class AppModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(AppActivityMapper.class).in(Singleton.class);
		bind(DataManager.class).to(DataManagerImpl.class).in(Singleton.class);
		bind(MVPSetup.class).in(Singleton.class);
		bind(TagView.TagPresenter.class).to(TagActivity.class).in(
				Singleton.class);
		bind(ImageView.ImagePresenter.class).annotatedWith(
				Names.named("fullscreen")).to(ImageActivity.class).in(
				Singleton.class);
		bind(ImageView.ImagePresenter.class).annotatedWith(
				Names.named("embedded")).to(EmbeddedImageActivity.class).in(
				Singleton.class);
		bind(EmbeddedImageActivity.class);
		bind(PlaceSwapper.class);
		bind(TagCell.class);
		bind(TagView.class).to(TagViewImpl.class);
		bind(ImageView.class).to(ImageViewImpl.class);
		bind(PlaceGoTo.class).to(PlaceGoToImpl.class);
		bind(PlaceWhere.class).to(PlaceWhereImpl.class);
		bind(PlaceController.class).toProvider(PlaceControllerProvider.class);
		bind(PlaceControllerProvider.class).in(Singleton.class);
		bind(EventBus.class).to(SimpleEventBus.class).in(Singleton.class);
		bind(KeyDispatcherProvider.class).in(Singleton.class);
		bind(ShortCutHandler.class).toProvider(KeyDispatcherProvider.class);
		bind(Slideshow.class).to(SlideshowTimer.class);
	}
}
