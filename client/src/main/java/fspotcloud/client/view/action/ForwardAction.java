package fspotcloud.client.view.action;

import com.google.inject.Inject;
import com.google.inject.Provider;

import fspotcloud.client.main.gin.ImagePresenterProvider;
import fspotcloud.client.view.ImageView;

public class ForwardAction implements GestureAction {

	Provider<ImageView.ImagePresenter> imagePresenterProvider;
	
	@Inject
	public ForwardAction(ImagePresenterProvider imagePresenterProvider) {
		this.imagePresenterProvider = imagePresenterProvider;
	}

	@Override
	public void perform() {
		ImageView.ImagePresenter presenter = imagePresenterProvider.get();
		if (presenter != null) {
			presenter.goForward();
		}
	}

}
