package fspotcloud.client.view.action;

import com.google.inject.Inject;

import fspotcloud.client.main.gin.ActivePagerPresenter;
import fspotcloud.client.view.PagerView.PagerPresenter;

public class ForwardAction implements GestureAction {

	ActivePagerPresenter provider;
	@Inject
	public ForwardAction(ActivePagerPresenter provider) {
		this.provider = provider;
	}
	
	@Override
	public void perform() {
		PagerPresenter presenter = provider.get();
		if (presenter != null) {
			presenter.go(true);
		}
	}

}
