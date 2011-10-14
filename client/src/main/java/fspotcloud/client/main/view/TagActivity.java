package fspotcloud.client.main.view;

import java.util.logging.Logger;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

import fspotcloud.client.main.view.api.TagView;

public class TagActivity extends AbstractActivity implements
		TagView.TagPresenter {
	
	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(TagActivity.class
			.getName());

	final private TagView tagView;

	public TagActivity(TagView tagView) {
		this.tagView = tagView;
	}

	@Override
	public void start(AcceptsOneWidget containerWidget, EventBus eventBus) {
		containerWidget.setWidget(tagView);
	}
}
