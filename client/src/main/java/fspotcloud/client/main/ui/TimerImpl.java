package fspotcloud.client.main.ui;

import java.util.logging.Logger;

import com.google.gwt.user.client.Timer;

import fspotcloud.client.main.view.api.TimerInterface;

public class TimerImpl extends Timer implements TimerInterface {

	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(TimerImpl.class
			.getName());

	private Runnable runnable;

	public TimerImpl() {
	}

	public void run() {
		runnable.run();
	}

	@Override
	public void setRunnable(Runnable runnable) {
		this.runnable = runnable;
	}
}
