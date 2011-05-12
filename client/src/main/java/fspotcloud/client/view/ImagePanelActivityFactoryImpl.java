package fspotcloud.client.view;

import com.google.inject.Inject;

import fspotcloud.client.main.Navigator;
import fspotcloud.client.main.Slideshow;
import fspotcloud.client.view.ImagePanelView.ImagePanelPresenter;

public class ImagePanelActivityFactoryImpl implements ImagePanelActivityFactory {

	final private ImagePanelView imagePanelView;
	final private Navigator navigator;
	final private Slideshow slideshow;

	@Inject
	public ImagePanelActivityFactoryImpl(ImagePanelView imagePanelView,
			Navigator navigator, Slideshow slideshow) {
		this.navigator = navigator;
		this.slideshow = slideshow;
		this.imagePanelView = imagePanelView;
	}

	@Override
	public ImagePanelPresenter get(BasePlace place) {
		ImagePanelPresenter presenter = new ImagePanelActivity(place,
				imagePanelView, navigator, slideshow);
		presenter.init();
		return presenter;
	}

}
