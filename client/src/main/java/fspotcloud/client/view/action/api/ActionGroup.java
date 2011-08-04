package fspotcloud.client.view.action.api;

import java.util.List;

public interface ActionGroup {
	List<UserAction> allActions();

	String getDescription();
}
