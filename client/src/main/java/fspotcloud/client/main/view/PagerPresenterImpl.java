package fspotcloud.client.main.view;

import java.util.logging.Logger;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

import fspotcloud.client.main.view.api.PagerView;
import fspotcloud.client.main.view.api.PagerView.PagerPresenter;
import fspotcloud.client.place.BasePlace;
import fspotcloud.client.place.api.Navigator;

public class PagerPresenterImpl implements PagerPresenter {

	final private static Logger log = Logger.getLogger(PagerPresenterImpl.class
			.getName());

	final private PagerView pagerView;
	final private Navigator navigator;
	final private BasePlace place;

	String photoId;
	String tagId;
	Integer offset = null;
	boolean fullscreenTarget = false;

	@Inject
	public PagerPresenterImpl(@Assisted BasePlace place, @Assisted PagerView pagerView,
			Navigator navigator) {
		this.place = place;
		this.navigator = navigator;
		this.pagerView = pagerView;
		pagerView.setPresenter(this);
		log.info("Created with navigator: " + navigator);
	}

	@Override
	public void go(boolean forward) {
		navigator.goAsync(forward);
	}

	@Override
	public void goEnd(boolean first) {
		navigator.goEndAsync(first);
	}

}
