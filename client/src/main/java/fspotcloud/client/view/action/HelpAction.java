package fspotcloud.client.view.action;

import fspotcloud.client.main.view.HelpPresenter;

public class HelpAction implements GestureAction {

	final private static int ACTION_SHOW = 0;
	final private static int ACTION_HIDE = 1;
	
	private HelpPresenter helpPresenter;
	private boolean isShowing = false;

	
	public HelpAction() {
		helpPresenter = new HelpPresenter();
	}

	@Override
	public void perform() {
		if (isShowing) {
			helpPresenter.hide();
			isShowing = false;
		} else {
			helpPresenter.show();
			isShowing = true;
		}
	}

}
