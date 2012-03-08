package com.googlecode.fspotcloud.client.main.view.factory;

import java.util.logging.Logger;

import com.google.inject.Inject;

import com.googlecode.fspotcloud.client.main.ui.TagViewImpl;
import com.googlecode.fspotcloud.client.main.view.TagActivity;
import com.googlecode.fspotcloud.client.main.view.api.ButtonPanelView;
import com.googlecode.fspotcloud.client.main.view.api.ImageRasterPresenterFactory;
import com.googlecode.fspotcloud.client.main.view.api.ImageRasterView;
import com.googlecode.fspotcloud.client.main.view.api.TagPresenterFactory;
import com.googlecode.fspotcloud.client.main.view.api.TagView;
import com.googlecode.fspotcloud.client.main.view.api.TagView.TagPresenter;
import com.googlecode.fspotcloud.client.main.view.api.TreeView.TreePresenter;
import com.googlecode.fspotcloud.client.place.BasePlace;

public class TagPresenterFactoryImpl implements TagPresenterFactory {
	@SuppressWarnings("unused")
	private static final Logger log = Logger
			.getLogger(TagPresenterFactoryImpl.class.getName());
	final private TagViewImpl tagView;
	final private TreePresenter treePresenter;
	final private ButtonPanelView.ButtonPanelPresenter buttonPanelPresenter;
	final private ImageRasterPresenterFactory rasterFactory;

	@Inject
	public TagPresenterFactoryImpl(TagView tagView,
			TreePresenter treePresenter,
			ButtonPanelView.ButtonPanelPresenter buttonPanelPresenter,
			ImageRasterPresenterFactory rasterFactory) {
		super();
		this.tagView = (TagViewImpl) tagView;
		this.buttonPanelPresenter = buttonPanelPresenter;
		this.treePresenter = treePresenter;
		this.rasterFactory = rasterFactory;
		init();
	}

	private void init() {
		treePresenter.init();
	}

	@Override
	public TagPresenter get(BasePlace place) {
		final ImageRasterView.ImageRasterPresenter rasterPresenter = rasterFactory
				.get(place, tagView.getImageRasterView());
		TagPresenter presenter = new TagActivity(tagView, rasterPresenter);
		treePresenter.setPlace(place);
		return presenter;
	}
}
