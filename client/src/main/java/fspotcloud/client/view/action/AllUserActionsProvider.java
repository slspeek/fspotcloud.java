package fspotcloud.client.view.action;

import javax.inject.Provider;

import com.google.inject.Inject;

import fspotcloud.client.view.action.api.AllUserActions;

public class AllUserActionsProvider implements Provider<AllUserActions> {

	final private AllUserActions actions;

	@Inject
	public AllUserActionsProvider(AllShortcuts actions) {
		actions.init();
		this.actions = actions;
	}

	@Override
	public AllUserActions get() {
		return actions;
	}

}
