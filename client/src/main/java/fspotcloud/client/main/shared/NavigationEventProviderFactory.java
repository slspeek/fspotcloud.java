package fspotcloud.client.main.shared;

public interface NavigationEventProviderFactory {
	NavigationEventProvider get(NavigationEvent.ActionType actionType);
}
