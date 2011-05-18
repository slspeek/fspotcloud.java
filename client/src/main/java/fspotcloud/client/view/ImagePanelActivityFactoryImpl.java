package fspotcloud.client.view;

import com.google.inject.Inject;

import fspotcloud.client.main.Navigator;
import fspotcloud.client.main.Slideshow;
import fspotcloud.client.view.ImagePanelView.ImagePanelPresenter;
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

	@Inject
	public ImagePanelActivityFactoryImpl(ImagePanelView embeddedImagePanelView, ImagePanelView fullscreenImagePanelView,
			Navigator navigator, Slideshow slideshow) {
		this.navigator = navigator;
		this.slideshow = slideshow;
		this.embeddedImagePanelView = embeddedImagePanelView;
		this.fullscreenImagePanelView = fullscreenImagePanelView;
	}

	@Override
	public ImagePanelPresenter getEmbedded(BasePlace place) {
		ImagePanelPresenter presenter = new ImagePanelActivity(place,
				embeddedImagePanelView, navigator, slideshow);
		presenter.init();
		return presenter;
	}

	@Override
	public ImagePanelPresenter getFullscreen(BasePlace place) {
		ImagePanelPresenter presenter = new FullscreenImagePanelActivity(place,
				fullscreenImagePanelView, navigator, slideshow);
		presenter.init();
		return presenter;
	}

}
