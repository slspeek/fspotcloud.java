package fspotcloud.client.view.action.api;

import fspotcloud.client.view.action.NavigationAction;

public interface NavigationActionFactory {
	NavigationAction get(int actionType);
}
