package fspotcloud.client.main.view.api;

import fspotcloud.client.view.action.api.UserAction;

public interface UserButtonViewFactory {
	UserButtonView get(UserAction action);
}
