package fspotcloud.client.main.shared;

public interface ApplicationEventFactory {
	ApplicationEvent get(ApplicationEvent.ActionType actionType);
}
