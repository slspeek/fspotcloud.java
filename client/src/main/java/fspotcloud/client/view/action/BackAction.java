package fspotcloud.client.view.action;

import com.google.inject.Inject;
import com.google.inject.name.Named;

import fspotcloud.client.view.AppActivityMapper;
import fspotcloud.client.view.ImageView;
import fspotcloud.client.view.PlaceWhere;

public class BackAction extends ImagePresenterAction implements GestureAction {

	@Inject
	public BackAction(
			@Named("fullscreen") ImageView.ImagePresenter imagePresenter,
			@Named("embedded") ImageView.ImagePresenter embeddedImagePresenter,
			AppActivityMapper mapper, PlaceWhere where) {
		super(imagePresenter, embeddedImagePresenter, mapper, where);
	}

	@Override
	public void perform() {
		ImageView.ImagePresenter presenter = target();
		if (presenter != null) {
			presenter.goBackward();
		}
	}

}
