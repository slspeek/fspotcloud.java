package fspotcloud.client.view;

import java.util.logging.Logger;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;

import fspotcloud.client.data.DataManager;
import fspotcloud.client.view.PagerView.PagerPresenter;
import fspotcloud.shared.photo.PhotoInfoStore;
import fspotcloud.shared.tag.TagNode;

public class ImageActivity extends AbstractActivity implements
		ImageView.ImagePresenter {
	private static final Logger log = Logger.getLogger(ImageActivity.class
			.getName());

	final private DataManager tagNodeProvider;
	final private ImageView imageView;
	final private PagerPresenter pager;

	String tagId;
	String photoId;

	@Inject
	public ImageActivity(ImageView imageView, DataManager dataManager,
			PagerPresenter pager) {
		this.imageView = imageView;
		this.tagNodeProvider = dataManager;
		this.pager = pager;
	}

	public void setPlace(BasePlace place) {
		tagId = place.getTagId();
		photoId = place.getPhotoId();
		TagNode node = tagNodeProvider.getTagNode(tagId);
		PhotoInfoStore store = node.getCachedPhotoList();
		log
				.info("setPlace called for tagId: " + tagId + " photoId: "
						+ photoId);
		setImage();
		if (!store.isEmpty()) {
			pager.setData(store);
			pager.setPlace(place);
		}
	}

	private void setImage() {
		if (photoId != null) {
			imageView.setImageUrl("/image?id=" + photoId);
		} else {
			log.warning("No photoId defined for tagId:  " + tagId);
		}
	}

	@Override
	public void start(AcceptsOneWidget containerWidget, EventBus eventBus) {
		log.info("Start image activity for tagId: " + tagId + "photoId: "
				+ photoId);
		imageView.setPresenter(this);
		containerWidget.setWidget(imageView);
	}

	@Override
	public void onStop() {
		pager.stop();
		super.onStop();
	}

	@Override
	public PagerPresenter getPager() {
		return pager;
	}
}
