package fspotcloud.client.main.view.factory;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.inject.Inject;

import fspotcloud.client.main.ui.TagViewFactory;
import fspotcloud.client.main.view.TagActivity;
import fspotcloud.client.main.view.api.TagPresenterFactory;
import fspotcloud.client.main.view.api.TagView.TagPresenter;
import fspotcloud.client.main.view.api.TreeView.TreePresenter;
import fspotcloud.client.place.BasePlace;

public class TagPresenterFactoryImpl implements TagPresenterFactory {
	private static final Logger log = Logger
			.getLogger(TagPresenterFactoryImpl.class.getName());
	final private TagViewFactory tagViewFactory;
	final private TreePresenter treePresenter;

	@Inject
	public TagPresenterFactoryImpl(TagViewFactory tagViewFactory,
			TreePresenter treePresenter) {
		this.tagViewFactory = tagViewFactory;
		this.treePresenter = treePresenter;
		log.info("Creating");
		treePresenter.init();
		log.info("Created");
	}

	@Override
	public TagPresenter get(BasePlace place) {
		log.info("in get");
		TagPresenter presenter = new TagActivity(place, tagViewFactory.get(),
				treePresenter);
		log.info("in get after creation of activity");
		try {
			log.info("befoore init");
			presenter.init();
		} catch (Exception e) {
			log.log(Level.SEVERE, "get in init", e);
		}
		return presenter;
	}
}
