package fspotcloud.client.view.action;

import java.util.logging.Logger;

import com.google.inject.Inject;

import fspotcloud.client.main.Navigator;

public class ToggleFullscreenAction implements GestureAction {

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
	public void perform() {
		navigator.toggleShowTagTree();
	}
}
