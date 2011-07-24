package fspotcloud.client.view.action.api;

import fspotcloud.client.main.view.LoadNewLocationAction;

public interface LoadNewLocationActionFactory {
	LoadNewLocationAction get(String url);
}
