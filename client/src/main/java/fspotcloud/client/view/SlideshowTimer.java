package fspotcloud.client.view;

import java.util.logging.Logger;

import com.google.gwt.user.client.Timer;

import fspotcloud.client.view.ImageView.ImagePresenter;

public class SlideshowTimer extends Timer {

	private static final Logger log = Logger.getLogger(SlideshowTimer.class
			.getName());
	
	private ImagePresenter presenter;

	public void run() {
		log.info("Slideshow timer about to call goForward");
		presenter.goForward();
	}

	public void setImagePresenter(ImageView.ImagePresenter presenter) {
		this.presenter = presenter;
	}

	@Override
	public void cancel() {
		super.cancel();
	}

	@Override
	public void scheduleRepeating(int periodMillis) {
		super.scheduleRepeating(periodMillis);
	}

}
