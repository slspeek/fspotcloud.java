package fspotcloud.client.view;

import com.google.gwt.view.client.SingleSelectionModel;
import com.google.inject.Inject;

import fspotcloud.client.data.DataManager;
import fspotcloud.client.main.ui.TagViewFactory;
import fspotcloud.client.view.TagView.TagPresenter;
import fspotcloud.shared.tag.TagNode;

public class TagPresenterFactoryImpl implements TagPresenterFactory {

	final private TagViewFactory tagViewFactory;
	final private DataManager dataManager;
	final private PlaceGoTo placeGoTo;

	@Inject
	public TagPresenterFactoryImpl(TagViewFactory tagViewFactory,
			DataManager dataManager, PlaceGoTo placeGoTo) {
		this.tagViewFactory = tagViewFactory;
		this.placeGoTo = placeGoTo;
		this.dataManager = dataManager;
	}

	@Override
	public TagPresenter get(BasePlace place) {
		TagPresenter presenter = new TagActivity(place, tagViewFactory.get(), dataManager,
				placeGoTo, new SingleSelectionModel<TagNode>());
		presenter.init();
		return presenter;
	}

}
