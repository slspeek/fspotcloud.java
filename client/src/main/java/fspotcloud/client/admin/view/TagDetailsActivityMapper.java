package fspotcloud.client.admin.view;

import java.util.logging.Logger;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;
import com.google.inject.Inject;

import fspotcloud.client.admin.view.api.TagDetailsActivityFactory;
import fspotcloud.client.admin.view.api.TagDetailsView;
import fspotcloud.client.place.TagPlace;

public class TagDetailsActivityMapper implements ActivityMapper {
	private static final Logger log = Logger
			.getLogger(TagDetailsActivityMapper.class.getName());

	final private TagDetailsActivityFactory tagDetailsActivityFactory;
	
	@Inject
	public TagDetailsActivityMapper(
			TagDetailsActivityFactory tagDetailsActivityFactory) {
		super();
		this.tagDetailsActivityFactory = tagDetailsActivityFactory;
	}
	@Override
	public Activity getActivity(Place place) {
		log.info("getActivity: " + place);
		TagDetailsView.TagDetailsPresenter activity = tagDetailsActivityFactory.get((TagPlace) place);
		activity.init();
		return activity;
	}
}
