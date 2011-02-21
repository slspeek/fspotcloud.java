package fspotcloud.client.view.action;

import com.google.inject.Inject;

import fspotcloud.client.main.gin.ActivePagerPresenter;
import fspotcloud.client.view.PagerView.PagerPresenter;

public class BackAction implements GestureAction {

	ActivePagerPresenter provider;
	@Inject
	public BackAction(ActivePagerPresenter provider) {
		this.provider = provider;
	}
	@Override
	public void perform() {
		PagerPresenter presenter = provider.get();
		if (presenter != null) {
			presenter.go(false);
		}
	}

}
