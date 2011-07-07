package fspotcloud.client.demo;

import fspotcloud.client.main.HelpContentGenerator;
import fspotcloud.client.view.action.Shortcut;
import fspotcloud.client.view.action.ShortcutHandler;

public class ShortcutDemoStep implements DemoStep {

	final private ShortcutHandler shortcutHandler;
	final private Shortcut shortcut;
	final private int pause;
	final private HelpContentGenerator generator = new HelpContentGenerator();

	public ShortcutDemoStep(ShortcutHandler shortcutHandler, Shortcut shortcut,
			int pause) {
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
