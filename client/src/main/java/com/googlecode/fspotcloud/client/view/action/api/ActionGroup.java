package com.googlecode.fspotcloud.client.view.action.api;

import java.util.List;

public interface ActionGroup {
	List<UserAction> allActions();
	
	//UserAction getByActionDef(ActionDef def);
	
	String getDescription();
}
