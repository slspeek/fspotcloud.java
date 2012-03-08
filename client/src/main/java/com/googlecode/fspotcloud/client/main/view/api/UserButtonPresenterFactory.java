package com.googlecode.fspotcloud.client.main.view.api;

import com.googlecode.fspotcloud.client.view.action.api.UserAction;

public interface UserButtonPresenterFactory {
	UserButtonView.UserButtonPresenter get(UserAction action);
}
