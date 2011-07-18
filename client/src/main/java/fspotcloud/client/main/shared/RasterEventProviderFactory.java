package fspotcloud.client.main.shared;


public interface RasterEventProviderFactory {
	RasterEventProvider get(RasterEvent.ActionType actionType);
}
