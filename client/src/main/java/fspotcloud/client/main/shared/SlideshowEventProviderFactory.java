package fspotcloud.client.main.shared;

public interface SlideshowEventProviderFactory {
	NavigationEventProvider get(int actionType);
}
