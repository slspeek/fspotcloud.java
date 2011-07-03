package fspotcloud.client.main;

import java.util.logging.Logger;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;

import fspotcloud.client.main.shared.SlideshowStatusEvent;
import fspotcloud.client.main.view.api.TimerInterface;

public class SlideshowImpl implements Slideshow {

	private static final Logger log = Logger.getLogger(SlideshowImpl.class
			.getName());
	final private Navigator navigator;
	final private TimerInterface timer;
	private boolean isRunning = false;
	final private float increaseFactor = 4f / 3f;
	private float delay = 4f;
	final private EventBus eventBus;

	@Inject
	public SlideshowImpl(Navigator navigator, TimerInterface timer, EventBus eventBus) {
		this.eventBus = eventBus;
		this.navigator = navigator;
		this.timer = timer;
		initTimer();
		log.info("Created");
	}

	private void initTimer() {
		timer.setRunnable(new Runnable() {
			@Override
			public void run() {
				navigator.canGoAsync(true, new AsyncCallback<Boolean>() {
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
			navigator.goAsync(true);
		} else {
			stop();
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
		fireStatusChanged();
	}

	@Override
	public void stop() {
		log.info("Stopping slideshow");
		isRunning = false;
		timer.cancel();
		fireStatusChanged();
	}

	@Override
	public float faster() {
		delay *= increaseFactor;
		fireStatusChanged();
		return delay();
	}

	@Override
	public float slower() {
		delay /= increaseFactor;
		fireStatusChanged();
		return delay();
	}

	private void fireStatusChanged() {
		eventBus.fireEvent(new SlideshowStatusEvent(isRunning, delay()));
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
