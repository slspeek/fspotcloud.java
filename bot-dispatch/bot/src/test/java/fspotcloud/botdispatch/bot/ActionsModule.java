package fspotcloud.botdispatch.bot;

import net.customware.gwt.dispatch.server.guice.ActionHandlerModule;

class ActionsModule extends ActionHandlerModule {

	@Override
	protected void configureHandlers() {
		bindHandler(TestAction.class, TestActionHandler.class);
	}

}