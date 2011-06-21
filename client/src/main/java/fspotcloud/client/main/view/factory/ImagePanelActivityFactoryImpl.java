package fspotcloud.client.main.view.factory;

import com.google.gwt.event.shared.EventBus;
import com.google.inject.Inject;

import fspotcloud.client.main.Navigator;
import fspotcloud.client.main.PagingNavigator;
import fspotcloud.client.main.Slideshow;
import fspotcloud.client.main.view.FullscreenImagePanelActivity;
import fspotcloud.client.main.view.ImagePanelActivity;
import fspotcloud.client.main.view.api.ImagePanelActivityFactory;
import fspotcloud.client.main.view.api.ImagePanelView;
import fspotcloud.client.main.view.api.ImagePanelView.ImagePanelPresenter;
import fspotcloud.client.place.BasePlace;
/**
 * *
 * Mimics assisted inject
 *
 */
public class ImagePanelActivityFactoryImpl implements ImagePanelActivityFactory {

	final private ImagePanelView embeddedImagePanelView;
	final private ImagePanelView fullscreenImagePanelView;
	final private Navigator navigator;
	final private Slideshow slideshow;
	final private EventBus eventBus;
	final private PagingNavigator pager;

	@Inject
	public ImagePanelActivityFactoryImpl(ImagePanelView embeddedImagePanelView, ImagePanelView fullscreenImagePanelView,
			Navigator navigator, Slideshow slideshow, EventBus eventBus, PagingNavigator pager) {
		this.navigator = navigator;
		this.slideshow = slideshow;
		this.embeddedImagePanelView = embeddedImagePanelView;
		this.fullscreenImagePanelView = fullscreenImagePanelView;
		this.eventBus = eventBus;
		this.pager = pager;
	}

	@Override
	public ImagePanelPresenter getEmbedded(BasePlace place) {
		ImagePanelPresenter presenter = new ImagePanelActivity(place,
				embeddedImagePanelView, navigator, slideshow, eventBus, pager);
		presenter.init();
		return presenter;
	}

	@Override
	public ImagePanelPresenter getFullscreen(BasePlace place) {
		ImagePanelPresenter presenter = new FullscreenImagePanelActivity(place,
				fullscreenImagePanelView, navigator, slideshow, eventBus, pager);
		presenter.init();
		return presenter;
	}

}
