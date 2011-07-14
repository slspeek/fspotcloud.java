package fspotcloud.client.view.action;

import fspotcloud.client.view.action.api.AllUserActions;

public interface DemoActionFactory {
	DemoAction get(AllUserActions actions);
}
