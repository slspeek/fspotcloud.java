package fspotcloud.client.main;

import java.util.logging.Logger;

import com.google.inject.Inject;

import fspotcloud.client.place.api.Navigator;


public class ToggleButtonsAction implements Runnable {

	@SuppressWarnings("unused")
	private static final Logger log = Logger
			.getLogger(ToggleButtonsAction.class.getName());

	final private Navigator navigator;

	@Inject
	public ToggleButtonsAction(Navigator navigator) {
		super();
		this.navigator = navigator;
	}

	@Override
	public void run() {
		navigator.toggleButtonsVisible();
	}
}
