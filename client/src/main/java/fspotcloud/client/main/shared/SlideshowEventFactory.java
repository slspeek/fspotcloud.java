package fspotcloud.client.main.shared;

public interface SlideshowEventFactory {
	SlideshowEvent get(SlideshowEvent.ActionType actionType);
}
