package fspotcloud.client.main.help;

import com.google.inject.Inject;

import fspotcloud.client.main.view.HelpPresenter;
import fspotcloud.client.view.action.api.AllUserActions;

public class HelpAction implements Runnable {

	private HelpPresenter helpPresenter;
	private boolean isShowing = false;
	private AllUserActions actions;

	@Inject
	public HelpAction(AllUserActions actions, HelpPresenter helpPresenter) {
		this.actions = actions;
		this.helpPresenter = helpPresenter;
	}

	@Override
	public void run() {
		if (isShowing) {
			helpPresenter.hide();
			isShowing = false;
		} else {
			helpPresenter.show();
			isShowing = true;
		}
	}

}
