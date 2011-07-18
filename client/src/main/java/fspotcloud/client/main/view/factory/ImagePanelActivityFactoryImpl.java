package fspotcloud.client.main.view.factory;

import com.google.inject.Inject;

import fspotcloud.client.main.view.api.EmbeddedImagePanelViewAssistedFactory;
import fspotcloud.client.main.view.api.FullscreenImagePanelViewAssistedFactory;
import fspotcloud.client.main.view.api.ImagePanelActivityFactory;
import fspotcloud.client.main.view.api.ImagePanelView.ImagePanelPresenter;
import fspotcloud.client.place.BasePlace;

public class ImagePanelActivityFactoryImpl implements ImagePanelActivityFactory {

	private final EmbeddedImagePanelViewAssistedFactory embeddedFactory;
	private final FullscreenImagePanelViewAssistedFactory fullscreenFactory;
	
	@Inject
	public ImagePanelActivityFactoryImpl(
			EmbeddedImagePanelViewAssistedFactory embeddedFactory,
			FullscreenImagePanelViewAssistedFactory fullscreenFactory) {
		super();
		this.embeddedFactory = embeddedFactory;
		this.fullscreenFactory = fullscreenFactory;
	}
	
	@Override
	public ImagePanelPresenter getEmbedded(BasePlace place) {
		ImagePanelPresenter presenter = embeddedFactory.getEmbedded(place);
		presenter.init();
		return presenter;
	}

	@Override
	public ImagePanelPresenter getFullscreen(BasePlace place) {
		ImagePanelPresenter presenter = fullscreenFactory.getFullscreen(place);
		presenter.init();
		return presenter;
	}
}
