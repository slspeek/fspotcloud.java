package fspotcloud.client.main.shared;

public interface RasterEventFactory {
	RasterEvent get(RasterEvent.ActionType actionType);
}
