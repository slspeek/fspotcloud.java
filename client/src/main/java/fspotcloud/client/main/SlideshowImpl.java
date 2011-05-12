package fspotcloud.client.main;

import java.util.logging.Logger;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;

import fspotcloud.client.view.TimerInterface;

public class SlideshowImpl implements Slideshow {

	private static final Logger log = Logger.getLogger(SlideshowImpl.class
			.getName());
	final private Navigator navigator;
	final private TimerInterface timer;
	private boolean isRunning = false;
	final private float increaseFactor = 4f / 3f;
	private float delay = 4f;

	@Inject
	public SlideshowImpl(Navigator navigator, TimerInterface timer) {
		this.navigator = navigator;
		this.timer = timer;
		initTimer();
		log.info("Created");
	}

	private void initTimer() {
		timer.setRunnable(new Runnable() {
			@Override
			public void run() {
				navigator.canGo(true, new AsyncCallback<Boolean>() {
					@Override
					public void onFailure(Throwable caught) {
						log.warning(caught.getMessage());
					}

					@Override
					public void onSuccess(Boolean result) {
						go(result);
					}

				});
			}
		});
	}

	private void go(boolean canGo) {
		if (canGo) {
			navigator.go(true);
		} else {
			timer.cancel();
			log.info("Timer stopped, because the end was reached.");
		}
	}

	private void reschedule() {
		if (isRunning) {
			timer.cancel();
			timer.scheduleRepeating((int) (1000 * delay));
		}
	}

	@Override
	public void start() {
		log.info("Starting slideshow");
		isRunning = true;
		reschedule();
	}

	@Override
	public void stop() {
		log.info("Stopping slideshow");
		isRunning = false;
		timer.cancel();
	}

	@Override
	public float faster() {
		delay *= increaseFactor;
		return delay();
	}

	@Override
	public float slower() {
		delay /= increaseFactor;
		return delay();
	}

	@Override
	public float delay() {
		return delay;
	}

	@Override
	public boolean isRunning() {
		return isRunning;
	}
}
