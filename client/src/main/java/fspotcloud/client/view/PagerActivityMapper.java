package fspotcloud.client.view;

import java.util.logging.Logger;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;
import com.google.inject.Inject;

import fspotcloud.client.data.DataManager;
import fspotcloud.client.view.PagerView.PagerPresenter;
import fspotcloud.shared.photo.PhotoInfoStore;
import fspotcloud.shared.tag.TagNode;

public class PagerActivityMapper implements ActivityMapper {
	private static final Logger log = Logger
			.getLogger(PagerActivityMapper.class.getName());

	final private PagerView.PagerPresenter pagerActivity;
	final private DataManager tagNodeProvider;

	@Inject
	public PagerActivityMapper(PagerPresenter pagerActivity,
			DataManager tagNodeProvider) {
		this.pagerActivity = pagerActivity;
		this.tagNodeProvider = tagNodeProvider;
		log.info("*Created: " + this);
	}

	@Override
	public Activity getActivity(Place place) {
		log.info("*getActivity called " + place + " from :" + this);
		if (place instanceof BasePlace) {
			BasePlace basePlace = (BasePlace) place;
			log.info("Before pagerActivity.setPlace" + this);
			pagerActivity.setPlace(basePlace);
			log.info("After pagerActivity.setPlace" + this);
			String tagId = basePlace.getTagId();
			TagNode node = tagNodeProvider.getTagNode(tagId);
			if (node != null) {
				PhotoInfoStore store = node.getCachedPhotoList();
				pagerActivity.setData(store);
			}
		} else {
			log.warning("getActivity called with non-BasePlace " + place);
		}
		return pagerActivity;
	}

}
