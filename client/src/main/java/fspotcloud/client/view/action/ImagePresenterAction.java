package fspotcloud.client.view.action;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.place.shared.Place;
import com.google.inject.Inject;
import com.google.inject.name.Named;

import fspotcloud.client.view.AppActivityMapper;
import fspotcloud.client.view.ImageActivity;
import fspotcloud.client.view.ImageView;
import fspotcloud.client.view.PlaceWhere;
import fspotcloud.client.view.TagActivity;

public abstract class ImagePresenterAction implements GestureAction {
	final private ImageView.ImagePresenter imagePresenter;
	final private ImageView.ImagePresenter emmbededImagePresenter;
	final private AppActivityMapper mapper;
	final private PlaceWhere where;

	@Inject
	public ImagePresenterAction(
			@Named("fullscreen") ImageView.ImagePresenter imagePresenter,
			@Named("embedded") ImageView.ImagePresenter emmbededImagePresenter,
			AppActivityMapper mapper, PlaceWhere where) {
		this.imagePresenter = imagePresenter;
		this.emmbededImagePresenter = emmbededImagePresenter;
		this.mapper = mapper;
		this.where = where;
	}

	protected ImageView.ImagePresenter target() {
		Place place = where.where();
		Activity activity = mapper.getActivity(place);
		if (activity instanceof TagActivity) {
			return emmbededImagePresenter;
		} else if (activity instanceof ImageActivity) {
			return imagePresenter;			
		} else {
			return null;
		}
	}
}
