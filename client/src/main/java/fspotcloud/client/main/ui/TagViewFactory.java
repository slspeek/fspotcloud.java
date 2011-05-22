package fspotcloud.client.main.ui;

import com.google.gwt.event.shared.EventBus;
import com.google.inject.Inject;

import fspotcloud.client.main.view.ImagePanelViewActivityMapper;
import fspotcloud.client.main.view.api.TagView;

public class TagViewFactory extends ViewFactory {

	final private TagView tagView;

	@Inject
	public TagViewFactory(EventBus eventBus, TagView tagView,
			ImagePanelViewActivityMapper imageMapper) {
		super(eventBus);
		this.tagView = tagView;
		register(imageMapper, tagView.getImageViewPanelContainer());
	}

	public TagView get() {
		return tagView;
	}

}
