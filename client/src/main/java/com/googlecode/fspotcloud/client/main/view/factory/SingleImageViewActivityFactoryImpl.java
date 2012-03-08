package com.googlecode.fspotcloud.client.main.view.factory;

import com.google.inject.Inject;

import com.googlecode.fspotcloud.client.main.ui.SingleImageViewImpl;
import com.googlecode.fspotcloud.client.main.view.SingleImageActivity;
import com.googlecode.fspotcloud.client.main.view.SlideshowControlsPresenter;
import com.googlecode.fspotcloud.client.main.view.api.ImageRasterPresenterFactory;
import com.googlecode.fspotcloud.client.main.view.api.ImageRasterView;
import com.googlecode.fspotcloud.client.main.view.api.SingleImageView.SingleImagePresenter;
import com.googlecode.fspotcloud.client.main.view.api.SingleViewActivityFactory;
import com.googlecode.fspotcloud.client.place.BasePlace;

public class SingleImageViewActivityFactoryImpl implements
		SingleViewActivityFactory {

	private final ImageRasterPresenterFactory imageRasterPresenterFactory;
	private final SingleImageViewImpl singleImageView;
	
	@SuppressWarnings("unused")
	private final SlideshowControlsPresenter controlsPresenter;

	@Inject
	public SingleImageViewActivityFactoryImpl(
			ImageRasterPresenterFactory imageRasterPresenterFactory,
			SingleImageViewImpl singleImageView, SlideshowControlsPresenter controlsPresenter) {
		super();
		this.controlsPresenter = controlsPresenter;
		this.imageRasterPresenterFactory = imageRasterPresenterFactory;
		this.singleImageView = singleImageView;
	}

	@Override
	public SingleImagePresenter get(BasePlace place) {
		ImageRasterView.ImageRasterPresenter raster = imageRasterPresenterFactory.get(place,
				singleImageView.getImageRasterView());
		return new SingleImageActivity(singleImageView, raster);
	}

}
