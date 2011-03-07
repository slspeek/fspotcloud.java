package fspotcloud.client.view;

import java.util.logging.Logger;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;

import fspotcloud.client.data.DataManager;
import fspotcloud.client.view.PagerView.PagerPresenter;
import fspotcloud.shared.photo.PhotoInfoStore;
import fspotcloud.shared.tag.TagNode;

public class PagerActivityMapper implements ActivityMapper {
	private static final Logger log = Logger
			.getLogger(PagerActivityMapper.class.getName());

	final private PagerPresenterFactory pagerPresenterFactory;
	final private DataManager tagNodeProvider;

	@Inject
	public PagerActivityMapper(PagerPresenterFactory pagerPresenterFactory,
			DataManager tagNodeProvider) {
		this.pagerPresenterFactory = pagerPresenterFactory;
		this.tagNodeProvider = tagNodeProvider;
	}

	@Override
	public Activity getActivity(Place place) {
		final PagerPresenter pagerActivity; 
		if (place instanceof BasePlace) {
			final BasePlace basePlace = (BasePlace) place;
			pagerActivity = pagerPresenterFactory.getPagerPresenter(basePlace);
			String tagId = basePlace.getTagId();
			tagNodeProvider.getTagNode(tagId, new AsyncCallback<TagNode>() {
				@Override
				public void onSuccess(TagNode arg0) {
					PhotoInfoStore store = arg0.getCachedPhotoList();
					pagerActivity.setData(store);
				}
				@Override
				public void onFailure(Throwable arg0) {
					log.warning(arg0.getLocalizedMessage());
				}
			});
		} else {
			log.warning("getActivity called with non-BasePlace " + place);
			pagerActivity = null;
		}
		return pagerActivity;
	}

}