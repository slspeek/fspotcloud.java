package fspotcloud.client.main;

import java.util.logging.Logger;

import com.google.inject.Inject;


public class ToggleFullscreenAction implements Runnable {

	@SuppressWarnings("unused")
	private static final Logger log = Logger
			.getLogger(ToggleFullscreenAction.class.getName());

	final private Navigator navigator;

	@Inject
	public ToggleFullscreenAction(Navigator navigator) {
		super();
		this.navigator = navigator;
	}

	@Override
	public void run() {
		navigator.toggleShowTagTree();
	}
}
