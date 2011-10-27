package fspotcloud.client.main.ui;

import com.google.gwt.user.client.Window;

import fspotcloud.client.main.view.api.LoadNewLocation;

public class LoadNewLocationImpl implements LoadNewLocation{

	@Override
	public void setLocation(String url) {
		//Window.Location.replace(url);
		Window.open(url, "", "");
	}

}
