package fspotcloud.client.view;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.inject.Inject;

import fspotcloud.client.main.ui.TagViewFactory;
import fspotcloud.client.view.TagView.TagPresenter;
import fspotcloud.client.view.TreeView.TreePresenter;

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
