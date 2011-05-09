package fspotcloud.client.view;

import java.util.logging.Logger;

import com.google.gwt.event.shared.EventBus;
import com.google.inject.Inject;

public class SlideShowPresenterImpl implements SlideshowView.SlideshowPresenter {
	private static final Logger log = Logger
			.getLogger(SlideShowPresenterImpl.class.getName());

	final private SlideshowView slideshowView;
	final private EventBus eventBus;
	private int interval = 3;

	@Inject
	public SlideShowPresenterImpl(SlideshowView slideshowView, EventBus eventBus) {
		this.slideshowView = slideshowView;
		this.eventBus = eventBus;
		slideshowView.setPresenter(this);
		log.info("SlideshowActivity Created");
	}

	private void redraw() {
		slideshowView.setLabelText(String.valueOf(interval) + " seconds. ");
	}

	@Override
	public void faster() {
	}

	@Override
	public void slower() {
		if (interval > 2) {
			interval--;
		}
	}

	@Override
	public void start() {
		// TODO Auto-generated method stub

	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub

	}
}
