package fspotcloud.client.main.gin;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.place.shared.Place;
import com.google.inject.Inject;
import com.google.inject.name.Named;

import fspotcloud.client.view.MainWindowActivityMapper;
import fspotcloud.client.view.ImageActivity;
import fspotcloud.client.view.PlaceWhere;
import fspotcloud.client.view.TagActivity;
import fspotcloud.client.view.ImageView.ImagePresenter;
import fspotcloud.client.view.PagerView.PagerPresenter;

public class ActivePagerPresenter {

	final private PagerPresenter pager;
	final private PagerPresenter emmbededPager;
	final private MainWindowActivityMapper mapper;
	final private PlaceWhere where;

	@Inject
	public ActivePagerPresenter(
			@Named("fullscreen") ImagePresenter imagePresenter,
			@Named("embedded") 	ImagePresenter emmbededImagePresenter,
			MainWindowActivityMapper mapper, PlaceWhere where) {
		this.pager= null;
		this.emmbededPager = null;
		this.mapper = mapper;
		this.where = where;
	}

	public PagerPresenter get() {
		Place place = where.where();
		Activity activity = mapper.getActivity(place);
		if (activity instanceof TagActivity) {
			return emmbededPager;
		} else if (activity instanceof ImageActivity) {
			return pager;
		} else {
			return null;
		}
	}
}
