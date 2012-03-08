package com.googlecode.fspotcloud.client.main.event;

import com.googlecode.fspotcloud.client.view.action.api.ActionDef;

public interface UserEventFactory {
	UserEvent get(ActionDef actionType);
}
