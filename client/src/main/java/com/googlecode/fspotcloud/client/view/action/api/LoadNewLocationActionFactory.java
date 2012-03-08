package com.googlecode.fspotcloud.client.view.action.api;

import com.googlecode.fspotcloud.client.main.view.LoadNewLocationAction;

public interface LoadNewLocationActionFactory {
	LoadNewLocationAction get(String url);
}
