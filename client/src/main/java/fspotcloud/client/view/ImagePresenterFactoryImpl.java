package fspotcloud.client.view;

import com.google.inject.Inject;

import fspotcloud.client.main.ui.ImageViewFactory;
import fspotcloud.client.view.ImageView.ImagePresenter;

public class ImagePresenterFactoryImpl implements ImagePresenterFactory {

	final private ImageView imageView;
	
	@Inject
	public ImagePresenterFactoryImpl(ImageViewFactory imageViewFactory) {
		this.imageView = imageViewFactory.get();
	}
	
	@Override
	public ImagePresenter get(BasePlace place) {
		ImagePresenter presenter = new ImageActivity(place, imageView);
		presenter.init();
		return presenter;
	}
}
