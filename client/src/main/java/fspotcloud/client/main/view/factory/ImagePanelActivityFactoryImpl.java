package fspotcloud.client.main.view.factory;

import com.google.inject.Inject;

import fspotcloud.client.main.view.api.EmbeddedImagePanelPresenterAssistedFactory;
import fspotcloud.client.main.view.api.FullscreenImagePanelPresenterAssistedFactory;
import fspotcloud.client.main.view.api.ImagePanelActivityFactory;
import fspotcloud.client.main.view.api.ImagePanelView.ImagePanelPresenter;
import fspotcloud.client.place.BasePlace;

public class ImagePanelActivityFactoryImpl implements ImagePanelActivityFactory {

	private final EmbeddedImagePanelPresenterAssistedFactory embeddedFactory;
	private final FullscreenImagePanelPresenterAssistedFactory fullscreenFactory;
	
	@Inject
	public ImagePanelActivityFactoryImpl(
			EmbeddedImagePanelPresenterAssistedFactory embeddedFactory,
			FullscreenImagePanelPresenterAssistedFactory fullscreenFactory) {
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
