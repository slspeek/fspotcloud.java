package fspotcloud.client.view;

import java.util.logging.Logger;

import com.google.gwt.user.client.Timer;
import com.google.inject.Inject;

import fspotcloud.client.view.ImageView.ImagePresenter;

public class SlideshowTimer extends Timer implements Slideshow {

	private static final Logger log = Logger.getLogger(SlideshowTimer.class
			.getName());
	
	ImagePresenter presenter;

	public void setPresenter(ImagePresenter presenter) {
		this.presenter = presenter;
	}

	boolean slideshowRunning = false;
	
	public void run() {
		log.info("Slideshow timer about to call goForward");
		presenter.goForward();
	}

	@Override
	public void stopSlideshow() {
		slideshowRunning = false;
		super.cancel();
	}

	@Override
	public void scheduleRepeating(int periodMillis) {
		slideshowRunning = true;
		super.scheduleRepeating(periodMillis);
	}

	public void toggleSlideshow() {
		if (slideshowRunning) {
			//imageView.setSlideshowButtonCaption("Start");
			cancel();
		} else {
			//imageView.setSlideshowButtonCaption("Stop");
			scheduleRepeating(3000);
		}
	}
}
