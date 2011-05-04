package fspotcloud.client.main.ui;

import com.google.gwt.event.shared.EventBus;
import com.google.inject.Inject;

import fspotcloud.client.view.ImageViewActivityMapper;
import fspotcloud.client.view.TagView;

public class TagViewFactory extends ViewFactory {

	final private TagView tagView;

	@Inject
	public TagViewFactory(EventBus eventBus, TagView tagView,
			ImageViewActivityMapper imageMapper) {
		super(eventBus);
		this.tagView = tagView;
		register(imageMapper, tagView.getImageViewContainer());
	}

	public TagView get() {
		return tagView;
	}

}
