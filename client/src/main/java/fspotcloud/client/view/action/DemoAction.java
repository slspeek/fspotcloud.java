package fspotcloud.client.view.action;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.google.gwt.user.client.Timer;

import fspotcloud.client.demo.DemoRunner;
import fspotcloud.client.demo.DemoStep;
import fspotcloud.client.demo.DemoStepFactory;
import fspotcloud.client.main.view.DemoPresenter;

public class DemoAction implements GestureAction {
	private static final Logger log = Logger.getLogger(KeyDispatcher.class
			.getName());
	List<DemoStep> demo;
	int stepPointer = -1;
	final private DemoStepFactory factory;

	public DemoAction(DemoStepFactory factory) {
		this.factory = factory;
		initDemo();
	}

	private void initDemo() {
		demo = new ArrayList<DemoStep>();
		addStep(AllShortcuts.SET_RASTER_2x2, 3000);
		addStep(AllShortcuts.SET_RASTER_3x3, 2000);
		addStep(AllShortcuts.SET_RASTER_4x4, 4000);
		addStep(AllShortcuts.ADD_COLUMN, 1000);
		addStep(AllShortcuts.ADD_COLUMN, 4000, "You can do this again and again");
		addStep(AllShortcuts.ADD_COLUMN, 4000, "And again");
		addStep(AllShortcuts.TOGGLE_TABULAR_VIEW, 2000);
		addStep(AllShortcuts.GOTO_END, 3000);
		addStep(AllShortcuts.GO_BACK, 3000);
		addStep(AllShortcuts.GOTO_START, 2000);
		addStep(AllShortcuts.GO_FORWARD, 3000);
		addStep(AllShortcuts.TOGGLE_TABULAR_VIEW, 1000, "Again to go back to tabular view");
		addStep(AllShortcuts.SET_DEFAULT_RASTER, 3000);
		addStep(AllShortcuts.TOGGLE_HELP, 5000);
		addStep(AllShortcuts.TOGGLE_HELP, 1000, "Again to hide the help.");
	}

	private void addStep(Shortcut shortcut, int pause) {
		DemoStep step = factory.getDemoStep(shortcut, pause);
		demo.add(step);
	}

	private void addStep(Shortcut shortcut, int pause,
			String descriptionOverride) {
		DemoStep step = factory.getDemoStep(new Shortcut(descriptionOverride,
				shortcut.getKey(), shortcut.getAlternateKey()), pause);
		demo.add(step);
	}

	@Override
	public void perform() {
		stepPointer++;
		if (stepPointer < demo.size()) {
			DemoStep step = demo.get(stepPointer);
			DemoRunner runner = new DemoRunner(step, this);
			final DemoPresenter demoPresenter = new DemoPresenter(
					step.getDescription());
			demoPresenter.show();
			Timer hideTimer = new Timer() {
				@Override
				public void run() {
					demoPresenter.hide();
				}
			};
			hideTimer.schedule(step.pauseTime());
		} else {
			log.info("Demo ended");
			final DemoPresenter demoPresenter = new DemoPresenter(
					"Demo ended.<br> Thank you.");
			demoPresenter.show();
			Timer hideTimer = new Timer() {
				@Override
				public void run() {
					demoPresenter.hide();
				}
			};
			hideTimer.schedule(3000);
			stepPointer = -1;
		}
	}

}
