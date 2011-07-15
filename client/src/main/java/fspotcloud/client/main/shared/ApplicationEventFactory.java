package fspotcloud.client.main.shared;



public interface ApplicationEventFactory {
	ApplicationEvent get(int actionType); 
}
