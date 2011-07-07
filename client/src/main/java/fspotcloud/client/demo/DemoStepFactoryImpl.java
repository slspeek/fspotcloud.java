package fspotcloud.client.demo;

import com.google.inject.Inject;

import fspotcloud.client.view.action.Shortcut;
import fspotcloud.client.view.action.ShortcutHandler;

public class DemoStepFactoryImpl implements DemoStepFactory {

	final private ShortcutHandler shortcutHandler;

	@Inject
	public DemoStepFactoryImpl(ShortcutHandler shortcutHandler) {
		this.shortcutHandler = shortcutHandler;
	}

	@Override
	public DemoStep getDemoStep(Shortcut shortcut, int pause) {
		return new ShortcutDemoStep(shortcutHandler, shortcut, pause);
	}
}
