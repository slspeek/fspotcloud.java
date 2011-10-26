package fspotcloud.client.main.help;

import com.google.inject.Inject;

import fspotcloud.client.main.view.HelpPresenter;

public class HelpAction implements Runnable {

	private HelpPresenter helpPresenter;
	@Inject
	public HelpAction(HelpPresenter helpPresenter) {
		this.helpPresenter = helpPresenter;
	}

	@Override
	public void run() {
		if (helpPresenter.isShowing()) {
			helpPresenter.hide();
		} else {
			helpPresenter.show();
		}
	}

}
