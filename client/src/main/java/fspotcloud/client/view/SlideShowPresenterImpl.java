package fspotcloud.client.view;

import java.util.logging.Logger;

import com.google.gwt.i18n.client.NumberFormat;
import com.google.inject.Inject;

import fspotcloud.client.main.Slideshow;

public class SlideShowPresenterImpl implements SlideshowView.SlideshowPresenter {
	private static final Logger log = Logger
			.getLogger(SlideShowPresenterImpl.class.getName());

	final private SlideshowView slideshowView;
	final private Slideshow slideshow;
	final private NumberFormat formatter = NumberFormat.getDecimalFormat();
	@Inject
	public SlideShowPresenterImpl(SlideshowView slideshowView,
			Slideshow slideshow) {
		this.slideshowView = slideshowView;
		this.slideshow = slideshow;
		slideshowView.setPresenter(this);
		log.info("SlideshowActivity Created");
	}

	@Override
	public void init() {
		redraw(slideshow.delay());
	}

	private void redraw(float delay) {
		slideshowView.setLabelText(formatter.format(delay) + " seconds. ");
	}

	@Override
	public void faster() {
		float delay = slideshow.faster();
		redraw(delay);
	}

	@Override
	public void slower() {
		float delay = slideshow.slower();
		redraw(delay);
	}

	@Override
	public void start() {
		slideshow.start();
	}

	@Override
	public void stop() {
		slideshow.stop();
	}

}
