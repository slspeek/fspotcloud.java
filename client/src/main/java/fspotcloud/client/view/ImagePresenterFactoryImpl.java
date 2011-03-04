package fspotcloud.client.view;

import com.google.gwt.event.shared.EventBus;
import com.google.inject.Inject;

import fspotcloud.client.view.ImageView.ImagePresenter;

public class ImagePresenterFactoryImpl implements ImagePresenterFactory {

	final private EventBus eventBus;
	final private ImageView imageView;
	final private PagerActivityMapper pagerActivityMapper;
	final private SlideshowActivityMapper slideshowActivityMapper;
	
	@Inject
	public ImagePresenterFactoryImpl(ImageView imageView, PagerActivityMapper pagerActivityMapper,
			SlideshowActivityMapper slideshowActivityMapper, EventBus eventBus) {
		this.eventBus = eventBus;
		this.imageView = imageView;
		this.pagerActivityMapper = pagerActivityMapper;
		this.slideshowActivityMapper = slideshowActivityMapper; 
	}
	
	@Override
	public ImagePresenter get(BasePlace place) {
		return new ImageActivity(place, imageView, pagerActivityMapper, slideshowActivityMapper, eventBus);
	}
}
