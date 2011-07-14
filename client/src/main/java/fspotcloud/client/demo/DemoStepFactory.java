package fspotcloud.client.demo;

import fspotcloud.client.view.action.api.UserAction;

public interface DemoStepFactory {

	DemoStep getDemoStep(UserAction shortcut, int pause);
}
