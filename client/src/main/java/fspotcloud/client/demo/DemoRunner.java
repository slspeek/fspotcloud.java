package fspotcloud.client.demo;

import com.google.gwt.user.client.Timer;

import fspotcloud.client.view.action.DemoAction;

public class DemoRunner extends Timer {

	final private DemoStep step;
	final private DemoAction action;

	public DemoRunner(DemoStep step, DemoAction action) {
		this.step = step;
		this.action = action;
		runThisStep();
		schedule(step.pauseTime());
	}
	
	private void runThisStep() {
		step.getAction().run();
	}

	@Override
	public void run() {
		action.perform();
	}

}
