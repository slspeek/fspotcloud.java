package fspotcloud.client.view;

import com.google.gwt.event.shared.EventBus;
import com.google.inject.Inject;

import fspotcloud.client.main.Navigator;
import fspotcloud.client.view.ImagePanelView.ImagePanelPresenter;

public class ImagePanelActivityFactoryImpl implements ImagePanelActivityFactory {

	final private ImagePanelView imagePanelView;
	final private Navigator navigator;
	final private EventBus eventBus;

	@Inject
	public ImagePanelActivityFactoryImpl(ImagePanelView imagePanelView,
			Navigator navigator, EventBus eventBus) {
		this.eventBus = eventBus;
		this.navigator = navigator;
		this.imagePanelView = imagePanelView;
	}

	@Override
	public ImagePanelPresenter get(BasePlace place) {
		ImagePanelPresenter presenter = new ImagePanelActivity(place, imagePanelView, eventBus,
				navigator);
		presenter.init();
		return presenter;
	}

}
