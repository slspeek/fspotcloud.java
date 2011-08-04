package fspotcloud.client.main.shared;


public interface NavigationEventFactory {
	NavigationEvent get(NavigationEvent.ActionType actionType);
}
