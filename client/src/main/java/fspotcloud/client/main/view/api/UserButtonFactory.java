package fspotcloud.client.main.view.api;

import fspotcloud.client.view.action.api.UserAction;

public interface UserButtonFactory {
	UserButtonView.UserButtonPresenter get(UserAction action);
}
