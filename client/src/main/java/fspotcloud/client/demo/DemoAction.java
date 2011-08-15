package fspotcloud.client.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.google.gwt.user.client.Timer;
import com.google.inject.Inject;
import com.google.inject.Provider;

import fspotcloud.client.main.view.DemoPresenter;
import fspotcloud.client.view.action.KeyDispatcher;
import fspotcloud.client.view.action.api.AllUserActions;
import fspotcloud.client.view.action.api.ShortcutAssistedFactory;
import fspotcloud.client.view.action.api.UserAction;

public class DemoAction implements Runnable {
	private static final Logger log = Logger.getLogger(KeyDispatcher.class
			.getName());
	List<DemoStep> demo;
	int stepPointer = -1;
	final private DemoStepFactory factory;
	final private AllUserActions actions;
	final private ShortcutAssistedFactory shortcutFactory;
	final private Provider<DemoPresenter> demoPresenterProvider; 

	@Inject
	public DemoAction(DemoStepFactory factory, ShortcutAssistedFactory shortcutFactory, AllUserActions actions, Provider<DemoPresenter> demoPresenterProvider) {
		this.factory = factory;
		this.actions = actions;
		this.shortcutFactory = shortcutFactory;
		this.demoPresenterProvider = demoPresenterProvider;
	}

	private void initDemo() {
		demo = new ArrayList<DemoStep>();
		addStep(actions.raster().setRaster2x2(), 3000);
		addStep(actions.raster().setRaster3x3(), 2000);
		addStep(actions.raster().setRaster4x4(), 4000);
		addStep(actions.raster().addColumm()	, 1000);
		addStep(actions.raster().addColumm()	, 4000, "You can do this again and again");
		addStep(actions.raster().addColumm()	, 4000, "And again");
		addStep(actions.raster().toggleTabularView(), 2000);
		addStep(actions.navigation().end(), 3000);
		addStep(actions.navigation().back(), 3000);
		addStep(actions.navigation().home(), 2000);
		addStep(actions.navigation().next(), 3000);
		addStep(actions.raster().toggleTabularView(), 2000, "Again to go back to tabular view");
		addStep(actions.application().toggleTreeVisible(), 2000, "Again to go back to tabular view");
		addStep(actions.raster().resetRaster(), 3000);
		addStep(actions.application().toggleHelp(), 5000);
		addStep(actions.application().toggleHelp(), 1000, "Again to hide the help.");
	}

	private void addStep(UserAction shortcut, int pause) {
		DemoStep step = factory.getDemoStep(shortcut, pause);
		demo.add(step);
	}

	private void addStep(UserAction shortcut, int pause,
			String descriptionOverride) {
		DemoStep step = factory.getDemoStep(shortcutFactory.get(shortcut.getId(), shortcut.getCaption(), descriptionOverride,
				shortcut.getKey(), shortcut.getAlternateKey(), shortcut.getIcon(), shortcut.getEventProvider()), pause);
		demo.add(step);
	}

	@Override
	public void run() {
		initDemo();
		stepPointer++;
		if (stepPointer < demo.size()) {
			DemoStep step = demo.get(stepPointer);
			DemoRunner runner = new DemoRunner(step, this);
			final DemoPresenter demoPresenter = demoPresenterProvider.get();
					demoPresenter.setText(step.getDescription());
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
			final DemoPresenter demoPresenter = demoPresenterProvider.get();
			demoPresenter.setText("Demo ended.<br> Thank you.");
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
