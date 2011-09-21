package fspotcloud.client.main.view.factory;

import com.google.inject.Inject;

import fspotcloud.client.main.view.api.ImagePanelPresenterAssistedFactory;
import fspotcloud.client.main.view.api.ImagePanelActivityFactory;
import fspotcloud.client.main.view.api.ImagePanelView.ImagePanelPresenter;
import fspotcloud.client.place.BasePlace;

public class ImagePanelActivityFactoryImpl implements ImagePanelActivityFactory {

	private final ImagePanelPresenterAssistedFactory factory;
	
	@Inject
	public ImagePanelActivityFactoryImpl(
			ImagePanelPresenterAssistedFactory factory
	) {
		super();
		this.factory = factory;
	}
	
	@Override
	public ImagePanelPresenter get(BasePlace place) {
		ImagePanelPresenter presenter = factory.get(place);
		presenter.init();
		return presenter;
	}
}
