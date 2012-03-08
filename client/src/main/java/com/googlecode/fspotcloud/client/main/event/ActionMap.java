package com.googlecode.fspotcloud.client.main.event;

import java.util.List;

import com.googlecode.fspotcloud.client.view.action.api.ActionDef;
import com.googlecode.fspotcloud.client.view.action.api.UserAction;

public interface ActionMap {
	List<UserAction> allActions();

	UserAction get(ActionDef def);

	String getDescription();
}
