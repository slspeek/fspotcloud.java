package fspotcloud.client.main.ui;

import java.util.logging.Logger;

import com.google.gwt.user.client.Timer;

import fspotcloud.client.view.TimerInterface;

public class SlideshowTimer extends Timer implements TimerInterface {

	private static final Logger log = Logger.getLogger(SlideshowTimer.class
			.getName());

	private Runnable runnable;

	public void run() {
		runnable.run();
	}

	@Override
	public void scheduleRepeating(int periodMillis) {
		super.scheduleRepeating(periodMillis);
	}

	@Override
	public void setRunnable(Runnable runnable) {
		this.runnable = runnable;
	}
}
