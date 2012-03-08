package com.googlecode.fspotcloud.client.main.gin;

import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;

import com.googlecode.fspotcloud.client.main.event.ActionFamily;
import com.googlecode.fspotcloud.client.main.event.TestEventModule;
import com.googlecode.fspotcloud.client.view.action.api.UserActionFactory;

@GinModules({TestEventModule.class})
public interface EventGinjector extends Ginjector {

	UserActionFactory getFactory();
	
	ActionFamily getAllActions();
	
}
