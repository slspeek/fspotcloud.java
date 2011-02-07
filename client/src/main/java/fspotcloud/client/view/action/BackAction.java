package fspotcloud.client.view.action;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.name.Named;

import fspotcloud.client.main.gin.ImagePresenterProvider;
import fspotcloud.client.view.AppActivityMapper;
import fspotcloud.client.view.ImageView;
import fspotcloud.client.view.PlaceWhere;

public class BackAction implements GestureAction {

	Provider<ImageView.ImagePresenter> imagePresenterProvider;
	@Inject
	public BackAction(
			ImagePresenterProvider imagePresenterProvider) {
		this.imagePresenterProvider = imagePresenterProvider;
	}
	@Override
	public void perform() {
		ImageView.ImagePresenter presenter = imagePresenterProvider.get();
		if (presenter != null) {
			presenter.goBackward();
		}
	}

}
