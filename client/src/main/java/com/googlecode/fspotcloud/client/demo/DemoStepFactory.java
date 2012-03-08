package com.googlecode.fspotcloud.client.demo;

import com.googlecode.fspotcloud.client.view.action.api.UserAction;

public interface DemoStepFactory {

	DemoStep getDemoStep(UserAction shortcut, int pause);
}
