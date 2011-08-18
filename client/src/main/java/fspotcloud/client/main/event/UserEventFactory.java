package fspotcloud.client.main.event;

import fspotcloud.client.view.action.api.ActionDef;

public interface UserEventFactory {
	UserEvent get(ActionDef actionType);
}
