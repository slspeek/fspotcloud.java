package fspotcloud.client.view;

import java.util.logging.Logger;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;

import fspotcloud.client.view.PagerView.PagerPresenter;
import fspotcloud.client.view.SlideshowView.SlideshowPresenter;

public class SlideShowActivity extends AbstractActivity implements
		SlideshowPresenter {
	private static final Logger log = Logger.getLogger(SlideShowActivity.class
			.getName());

	final private SlideshowView slideshowView;
	final private TimerInterface timer;
	private int interval = 3;
	private boolean isRunning = false;
	private PagerPresenter pager;

	@Inject
	public SlideShowActivity(TimerInterface timer, SlideshowView slideshowView) {
		this.slideshowView = slideshowView;
		this.timer = timer;
		initTimer();
		log.info("SlideshowActivity Created");
	}

	private void initTimer() {
		timer.setRunnable(new Runnable() {
			@Override
			public void run() {
				if (pager.canGo(true)) {
					pager.go(true);
				} else {
					timer.cancel();
				}
			}
		});
	}

	private void reschedule() {
		if (isRunning) {
			timer.cancel();
			timer.scheduleRepeating(1000 * interval);
		}
		redraw();
	}

	private void redraw() {
		slideshowView.setLabelText(String.valueOf(interval) + " seconds. ");
	}

	@Override
	public void faster() {
		if (interval < 16) {
			interval++;
			reschedule();
		}
	}

	@Override
	public void onStop() {
		stop();
		super.onStop();
	}

	@Override
	public void slower() {
		if (interval > 2) {
			interval--;
			reschedule();
		}
	}

	@Override
	public void start() {
		log.info("Starting slide show");
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
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		slideshowView.setSlideshowPresenter(this);
		panel.setWidget(slideshowView);
		redraw();
	}

	@Override
	public void setPresenter(PagerPresenter presenter) {
		this.pager = presenter;
	}
}
