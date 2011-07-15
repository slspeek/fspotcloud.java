package fspotcloud.client.main.shared;

public interface ApplicationEventProviderFactory {
	ApplicationEventProvider get(int actionType);
}
