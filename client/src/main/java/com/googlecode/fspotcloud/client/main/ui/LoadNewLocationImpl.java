package com.googlecode.fspotcloud.client.main.ui;

import com.google.gwt.user.client.Window;

import com.googlecode.fspotcloud.client.main.view.api.LoadNewLocation;

public class LoadNewLocationImpl implements LoadNewLocation{

	@Override
	public void setLocation(String url) {
		//Window.Location.replace(url);
		Window.open(url, "", "");
	}

}
