package com.googlecode.fspotcloud.client.main.view.factory;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import com.googlecode.fspotcloud.client.main.view.api.ImageView;
import com.googlecode.fspotcloud.client.main.view.api.ImageViewFactory;

public class ImageViewFactoryImpl implements ImageViewFactory {

	private Map<String, ImageView> imageViewCache = new HashMap<String, ImageView>();
	ImageViewFactory factory;

	@Inject
	public ImageViewFactoryImpl(ImageViewFactory factory) {
		super();
		this.factory = factory;
	}

	@Override
	public ImageView get(String location) {
		ImageView view = imageViewCache.get(location);
		if (view == null) {
			view = factory.get(location);
			imageViewCache.put(location, view);
		}
		return view;
	}

}
