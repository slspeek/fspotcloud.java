package fspotcloud.client.main.gin;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.inject.client.AbstractGinModule;
import com.google.gwt.place.shared.PlaceController;
import com.google.inject.Singleton;

import fspotcloud.client.main.AppActivityMapper;
import fspotcloud.client.main.DataManager;
import fspotcloud.client.main.ImageActivity;
import fspotcloud.client.main.ImageView;
import fspotcloud.client.main.MVPSetup;
import fspotcloud.client.main.TagActivity;
import fspotcloud.client.main.TagCell;
import fspotcloud.client.main.TagView;
import fspotcloud.client.main.ui.ImageViewImpl;
import fspotcloud.client.main.ui.TagViewImpl;

public class AppModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(AppActivityMapper.class).in(Singleton.class);
		bind(DataManager.class).in(Singleton.class);
		bind(MVPSetup.class).in(Singleton.class);
		bind(TagActivity.class);
		bind(ImageActivity.class);
		bind(TagCell.class);
		bind(TagView.class).to(TagViewImpl.class);
		bind(ImageViewImpl.class);
		bind(ImageView.class).to(ImageViewImpl.class);
		bind(PlaceController.class).toProvider(PlaceControllerProvider.class);
		bind(PlaceControllerProvider.class).in(Singleton.class);
		bind(EventBus.class).to(SimpleEventBus.class).in(Singleton.class);
	}
}
