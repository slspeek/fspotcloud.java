package fspotcloud.client.main.shared;

public interface NavigationEventProviderFactory {
	NavigationEventProvider get(int actionType);
}
