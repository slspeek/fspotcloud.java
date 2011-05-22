package fspotcloud.client.view;

import java.util.logging.Logger;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.inject.Inject;

import fspotcloud.client.main.Slideshow;
import fspotcloud.client.main.shared.SlideshowStatusEvent;

public class SlideShowPresenterImpl implements
		SlideshowView.SlideshowPresenter, SlideshowStatusEvent.Handler {
	private static final Logger log = Logger
			.getLogger(SlideShowPresenterImpl.class.getName());

	final private SlideshowView slideshowView;
	final private Slideshow slideshow;
	final private NumberFormat formatter = NumberFormat.getDecimalFormat();

	final private EventBus eventBus;
	private HandlerRegistration registration;

	@Inject
	public SlideShowPresenterImpl(SlideshowView slideshowView,
			Slideshow slideshow, EventBus eventBus) {
		this.eventBus = eventBus;
		this.slideshowView = slideshowView;
		this.slideshow = slideshow;
		slideshowView.setPresenter(this);
		log.info("SlideshowActivity Created");
	}

	@Override
	public void init() {
		registration = eventBus.addHandler(SlideshowStatusEvent.TYPE, this);
		redraw(slideshow.delay(), slideshow.isRunning());
	}

	public void cleanup() {
		registration.removeHandler();
	}

	private void redraw(float delay, boolean running) {
		slideshowView.setLabelText(formatter.format(delay) + " seconds. ");
		if (running) {
			slideshowView.asWidget().addStyleDependentName("running");
		} else {
			slideshowView.asWidget().removeStyleDependentName("running");
		}
	}

	@Override
	public void faster() {
		slideshow.faster();
	}

	@Override
	public void slower() {
		slideshow.slower();
	}

	@Override
	public void start() {
		slideshow.start();
	}

	@Override
	public void stop() {
		slideshow.stop();
	}

	@Override
	public void onEvent(SlideshowStatusEvent e) {
		redraw(e.getDelay(), e.isRunning());
	}

}
