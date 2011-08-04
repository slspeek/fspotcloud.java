package fspotcloud.client.demo;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

import fspotcloud.client.main.help.HelpContentGenerator;
import fspotcloud.client.view.action.api.ShortcutHandler;
import fspotcloud.client.view.action.api.UserAction;

public class ShortcutDemoStep implements DemoStep {

	final private ShortcutHandler shortcutHandler;
	final private UserAction shortcut;
	final private int pause;
	final private HelpContentGenerator generator = new HelpContentGenerator();

	@Inject
	public ShortcutDemoStep(ShortcutHandler shortcutHandler, @Assisted UserAction shortcut,
			@Assisted int pause) {
		this.shortcutHandler = shortcutHandler;
		this.shortcut = shortcut;
		this.pause = pause;
	}

	@Override
	public Runnable getAction() {
		return new Runnable() {
			@Override
			public void run() {
				shortcutHandler.handle(shortcut.getKey().getKeyCode());
			}
		};
	}

	@Override
	public int pauseTime() {
		return pause;
	}

	@Override
	public String getDescription() {
		return generator.getHelpText(shortcut);
	}
}
