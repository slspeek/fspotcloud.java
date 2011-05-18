package fspotcloud.client.view;

import java.util.logging.Logger;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

import fspotcloud.client.view.TreeView.TreePresenter;

public class TagActivity extends AbstractActivity implements
		TagView.TagPresenter {
	private static final Logger log = Logger.getLogger(TagActivity.class
			.getName());

	final private TagView tagView;
	final private BasePlace place;
	final private String tagId;
	final private TreePresenter treePresenter;

	public TagActivity(BasePlace place, TagView tagView,
			TreePresenter treePresenter) {
		this.place = place;
		this.tagView = tagView;
		this.treePresenter = treePresenter;
		tagId = place.getTagId();
		log.info("TagActivity Created for " + tagId + " " + place.getPhotoId());
	}

	public void init() {
		treePresenter.setPlace(place);
	}

	@Override
	public void start(AcceptsOneWidget containerWidget, EventBus eventBus) {
		log.info("Start tag activity for tagId: " + tagId);
		containerWidget.setWidget(tagView);
	}
}
