package fspotcloud.client.main.help;

import com.google.inject.Inject;

import fspotcloud.client.main.view.AboutPresenter;

public class AboutAction implements Runnable {

	private AboutPresenter aboutPresenter;
	private boolean isShowing = false;

	@Inject
	public AboutAction(AboutPresenter aboutPresenter) {
		this.aboutPresenter = aboutPresenter;
	}

	@Override
	public void run() {
		if (isShowing) {
			aboutPresenter.hide();
			isShowing = false;
		} else {
			aboutPresenter.show();
			isShowing = true;
		}
	}

}
