package fspotcloud.client.util;

import fspotcloud.client.main.ui.TimerImpl;
import fspotcloud.client.main.view.api.TimerInterface;

public class TimerUtil {

	static void runLater(int delay, Runnable task) {
		TimerInterface timer = new TimerImpl();
		timer.setRunnable(task);
		timer.schedule(delay);
	}
	
	
}
