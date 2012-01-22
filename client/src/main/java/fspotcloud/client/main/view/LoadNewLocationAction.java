package fspotcloud.client.main.view;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

import fspotcloud.client.main.view.api.LoadNewLocation;

public class LoadNewLocationAction implements Runnable {

	private final LoadNewLocation loader;
	private String newLocation;
	@Inject
	public LoadNewLocationAction(LoadNewLocation loader, @Assisted String newLocation) {
		super();
		this.loader = loader;
		this.newLocation = newLocation;
	}
	

	@Override
	public void run() {
		loader.setLocation(newLocation);
	}

	
}
