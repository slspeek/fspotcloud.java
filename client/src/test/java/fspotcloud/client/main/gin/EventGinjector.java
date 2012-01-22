package fspotcloud.client.main.gin;

import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;

import fspotcloud.client.main.event.ActionFamily;
import fspotcloud.client.main.event.TestEventModule;
import fspotcloud.client.view.action.api.UserActionFactory;

@GinModules({TestEventModule.class})
public interface EventGinjector extends Ginjector {

	UserActionFactory getFactory();
	
	ActionFamily getAllActions();
	
}
