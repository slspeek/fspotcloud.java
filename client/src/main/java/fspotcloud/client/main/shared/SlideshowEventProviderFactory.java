package fspotcloud.client.main.shared;

public interface SlideshowEventProviderFactory {
	SlideshowEventProvider get(SlideshowEvent.ActionType actionType);
}
