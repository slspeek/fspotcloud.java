package fspotcloud.client.view;

import com.google.inject.Inject;

import fspotcloud.client.view.PagerView.PagerPresenter;

public class PagerPresenterFactoryImpl implements PagerPresenterFactory {

	final private PagerView pagerView;
	final protected PlaceGoTo placeGoTo;

	@Inject
	public PagerPresenterFactoryImpl(PagerView pagerView, PlaceGoTo placeGoTo) {
		this.placeGoTo = placeGoTo;
		this.pagerView = pagerView;
	}

	@Override
	public PagerPresenter getPagerPresenter(BasePlace place) {
		return new PagerActivity(place, pagerView, placeGoTo);
	}

}
