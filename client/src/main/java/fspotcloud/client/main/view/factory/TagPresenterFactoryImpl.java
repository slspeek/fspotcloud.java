package fspotcloud.client.main.view.factory;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.inject.Inject;

import fspotcloud.client.main.ui.TagViewImpl;
import fspotcloud.client.main.view.TagActivity;
import fspotcloud.client.main.view.api.ButtonPanelView;
import fspotcloud.client.main.view.api.ImageRasterPresenterFactory;
import fspotcloud.client.main.view.api.ImageRasterView;
import fspotcloud.client.main.view.api.TagPresenterFactory;
import fspotcloud.client.main.view.api.TagView.TagPresenter;
import fspotcloud.client.main.view.api.TreeView.TreePresenter;
import fspotcloud.client.place.BasePlace;

public class TagPresenterFactoryImpl implements TagPresenterFactory {
	private static final Logger log = Logger
			.getLogger(TagPresenterFactoryImpl.class.getName());
	final private TagViewImpl tagView;
	final private TreePresenter treePresenter;
	final private ButtonPanelView.ButtonPanelPresenter buttonPanelPresenter;
	final private ImageRasterPresenterFactory rasterFactory;

	@Inject
	public TagPresenterFactoryImpl(TagViewImpl tagView,
			TreePresenter treePresenter,
			ButtonPanelView.ButtonPanelPresenter buttonPanelPresenter,
			ImageRasterPresenterFactory rasterFactory) {
		super();
		this.tagView = tagView;
		this.buttonPanelPresenter = buttonPanelPresenter;
		this.treePresenter = treePresenter;
		this.rasterFactory = rasterFactory;
		init();
	}

	private void init() {
		treePresenter.init();
		buttonPanelPresenter.init();
	}

	@Override
	public TagPresenter get(BasePlace place) {
		TagPresenter presenter = new TagActivity(tagView);
		try {
			treePresenter.setPlace(place);
			ImageRasterView.ImageRasterPresenter rasterPresenter = rasterFactory
					.get(place, tagView.getImageRasterView());
			rasterPresenter.init();
		} catch (Exception e) {
			log.log(Level.SEVERE, "get in init", e);
		}
		return presenter;
	}
}
