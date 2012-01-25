package fspotcloud.server.inject;

import net.customware.gwt.dispatch.server.guice.ActionHandlerModule;
import fspotcloud.server.main.user.GetUserInfoHandler;
import fspotcloud.shared.main.actions.GetUserInfo;

public class UserActionModule extends ActionHandlerModule {

	@Override
	protected void configureHandlers() {
		bindHandler(GetUserInfo.class, GetUserInfoHandler.class);
	}
}