package fspotcloud.client.main.view;

import java.util.logging.Logger;

import com.google.gwt.event.shared.EventBus;

import fspotcloud.client.main.shared.ZoomViewEvent;
import fspotcloud.client.main.view.api.ImageView;
import fspotcloud.client.place.BasePlace;

public class ImagePresenterImpl implements ImageView.ImagePresenter {
	private static final Logger log = Logger.getLogger(ImagePresenterImpl.class
			.getName());

	final int maxWidth;
	final int maxHeight;
	final private ImageView imageView;
	final private String tagId;
	final private String photoId;
	final private boolean thumb;
	final private EventBus eventBus;

	public ImagePresenterImpl(int maxWidth, int maxHeight, BasePlace place,
			ImageView imageView, boolean thumb, EventBus eventBus) {
		this.maxWidth = maxWidth;
		this.maxHeight = maxHeight;
		tagId = place.getTagId();
		photoId = place.getPhotoId();
		this.imageView = imageView;
		this.thumb = thumb;
		this.eventBus = eventBus;
	}

	public void init() {
		log.info("init");
		imageView.setPresenter(this);
		setImage();
		imageView.setMaxWidth(maxWidth);
		imageView.setMaxHeight(maxHeight);
	}

	public void setImage() {
		if (photoId != null) {
			String url = "/image?id=" + photoId;
			url += thumb ? "&thumb" : "";
			imageView.setImageUrl(url);
		} else {
			log.warning("No photoId defined for tagId:  " + tagId);
		}
	}

	@Override
	public void imageClicked() {
		log.info("about to fire zoom event");
		eventBus.fireEvent(new ZoomViewEvent(tagId, photoId));
	}
}
