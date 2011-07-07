package fspotcloud.client.demo;

import fspotcloud.client.view.action.Shortcut;

public interface DemoStepFactory {

	DemoStep getDemoStep(Shortcut shortcut, int pause);
}
