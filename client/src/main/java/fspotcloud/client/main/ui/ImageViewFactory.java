package fspotcloud.client.main.ui;

import com.google.gwt.event.shared.EventBus;
import com.google.inject.Inject;

import fspotcloud.client.view.ImageView;
import fspotcloud.client.view.PagerActivityMapper;
import fspotcloud.client.view.SlideshowActivityMapper;

public class ImageViewFactory extends ViewFactory {

	final private ImageView imageView;
	private final PagerActivityMapper pagerMapper;
	private final SlideshowActivityMapper slideshowMapper;

	@Inject
	public ImageViewFactory(EventBus eventBus, ImageView imageView,
			PagerActivityMapper pagerMapper,
			SlideshowActivityMapper slideshowMapper) {
		super(eventBus);
		this.imageView = imageView;
		this.pagerMapper = pagerMapper;
		this.slideshowMapper = slideshowMapper;
		register(pagerMapper, imageView.getPagerViewContainer());
		register(slideshowMapper, imageView.getSlideshowViewContainer());
	}

	public ImageView get() {
		
		return imageView;
	}

}