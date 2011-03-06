package fspotcloud.client.view;

import fspotcloud.client.view.PagerView.PagerPresenter;

public class PagerPresenterFactoryTestImpl implements PagerPresenterFactory {

	final private PlaceGoTo placeGoTo;
	public PagerPresenterFactoryTestImpl(PlaceGoTo placeGoTo) {
		this.placeGoTo = placeGoTo;
	}
	@Override
	public PagerPresenter getPagerPresenter(BasePlace place) {
		return new PagerActivity(place, new PagerViewDummy(), placeGoTo);
	}

}
