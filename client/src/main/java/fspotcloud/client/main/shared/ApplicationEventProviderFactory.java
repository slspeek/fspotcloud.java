package fspotcloud.client.main.shared;

public interface ApplicationEventProviderFactory {
	ApplicationEventProvider get(ApplicationEvent.ActionType actionType);
}
