package fspotcloud.client.main.view.factory;

import com.google.inject.Inject;

import fspotcloud.client.main.ui.SingleImageViewImpl;
import fspotcloud.client.main.view.SingleImageActivity;
import fspotcloud.client.main.view.SlideshowControlsPresenter;
import fspotcloud.client.main.view.api.ImageRasterPresenterFactory;
import fspotcloud.client.main.view.api.ImageRasterView;
import fspotcloud.client.main.view.api.SingleImageView.SingleImagePresenter;
import fspotcloud.client.main.view.api.SingleViewActivityFactory;
import fspotcloud.client.place.BasePlace;

public class SingleImageViewActivityFactoryImpl implements
		SingleViewActivityFactory {

	private final ImageRasterPresenterFactory imageRasterPresenterFactory;
	private final SingleImageViewImpl singleImageView;
	private final SlideshowControlsPresenter controlsPresenter;

	@Inject
	public SingleImageViewActivityFactoryImpl(
			ImageRasterPresenterFactory imageRasterPresenterFactory,
			SingleImageViewImpl singleImageView, SlideshowControlsPresenter controlsPresenter) {
		super();
		this.controlsPresenter = controlsPresenter;
		this.imageRasterPresenterFactory = imageRasterPresenterFactory;
		this.singleImageView = singleImageView;
		this.controlsPresenter.init();
	}

	@Override
	public SingleImagePresenter get(BasePlace place) {
		ImageRasterView.ImageRasterPresenter raster = imageRasterPresenterFactory.get(place,
				singleImageView.getImageRasterView());
		return new SingleImageActivity(place, singleImageView, raster);
	}

}
