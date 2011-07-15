package fspotcloud.client.view.action;

import com.google.inject.Inject;

import fspotcloud.client.main.view.HelpPresenter;
import fspotcloud.client.view.action.api.AllUserActions;

public class HelpAction implements Runnable {

	private HelpPresenter helpPresenter;
	private boolean isShowing = false;
	private AllUserActions actions;

	@Inject
	public HelpAction(AllUserActions actions) {
		this.actions = actions;
	}

	@Override
	public void run() {
		if (helpPresenter == null) {
			helpPresenter = new HelpPresenter(actions);
		}
		if (isShowing) {
			helpPresenter.hide();
			isShowing = false;
		} else {
			helpPresenter.show();
			isShowing = true;
		}
	}

}
