package fspotcloud.client.main.event;

import java.util.List;

import fspotcloud.client.view.action.api.ActionDef;
import fspotcloud.client.view.action.api.UserAction;

public interface ActionMap {
	List<UserAction> allActions();

	UserAction get(ActionDef def);

	String getDescription();
}
