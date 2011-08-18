package fspotcloud.client.main.event;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gwt.resources.client.ImageResource;
import com.google.inject.Provider;

import fspotcloud.client.view.action.api.ActionDef;
import fspotcloud.client.view.action.api.UserAction;
import fspotcloud.client.view.action.api.UserActionFactory;

public abstract class AbstractActionMap implements ActionMap {

	final protected UserActionFactory userActionFactory;
	
	Map<ActionDef,UserAction> actionMap = new HashMap<ActionDef, UserAction>();
	final private String description;
	
	public AbstractActionMap(UserActionFactory userActionFactory, String description) {
		this.userActionFactory = userActionFactory;
		this.description = description;
	}
	@Override
	public List<UserAction> allActions() {
		return new ArrayList<UserAction>(actionMap.values());
	}

	@Override
	public UserAction get(ActionDef def) {
		return actionMap.get(def);
	}

	@Override
	public String getDescription() {
		return description;
	}
	
	public void put(ActionDef actionDef, ImageResource icon, Provider<UserEvent<? extends UserEventHandler>> eventProvider) {
		UserAction action = userActionFactory.get(actionDef.getId(),
				actionDef.getCaption(), actionDef.getDescription(),
				actionDef.getKey(), actionDef.getAlternateKey(), icon, eventProvider);
		actionMap.put(actionDef, action);
	}
	
	public abstract void buildMap();

}
