package fspotcloud.client.view.action;

import fspotcloud.client.main.view.HelpPresenter;
import fspotcloud.client.view.action.api.AllUserActions;

public class HelpAction implements Runnable {

	private HelpPresenter helpPresenter;
	private boolean isShowing = false;

	public HelpAction(AllUserActions actions) {
		helpPresenter = new HelpPresenter(actions);
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
