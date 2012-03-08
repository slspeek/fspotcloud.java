package com.googlecode.fspotcloud.server.inject;

import net.customware.gwt.dispatch.server.guice.ActionHandlerModule;
import com.googlecode.fspotcloud.server.main.user.GetUserInfoHandler;
import com.googlecode.fspotcloud.shared.main.actions.GetUserInfo;

public class UserActionModule extends ActionHandlerModule {

	@Override
	protected void configureHandlers() {
		bindHandler(GetUserInfo.class, GetUserInfoHandler.class);
	}
}