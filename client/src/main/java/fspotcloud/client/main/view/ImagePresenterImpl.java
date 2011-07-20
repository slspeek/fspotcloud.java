package fspotcloud.client.main.view;

import java.util.logging.Logger;

import com.google.gwt.event.shared.EventBus;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

import fspotcloud.client.main.shared.ZoomViewEvent;
import fspotcloud.client.main.view.api.ImageView;
import fspotcloud.client.place.BasePlace;

public class ImagePresenterImpl implements ImageView.ImagePresenter {
	private static final Logger log = Logger.getLogger(ImagePresenterImpl.class
			.getName());

	final private ImageView imageView;
	final private String tagId;
	final private String photoId;
	final private boolean thumb;
	final private EventBus eventBus;

	@Inject
	public ImagePresenterImpl(@Assisted("maxWidth") int maxWidth,
			@Assisted("maxHeight") int maxHeight, @Assisted BasePlace place,
			@Assisted ImageView imageView, @Assisted boolean thumb,
			EventBus eventBus) {
		tagId = place.getTagId();
		photoId = place.getPhotoId();
		this.imageView = imageView;
		this.thumb = thumb;
		this.eventBus = eventBus;
		setMaxWidth(maxWidth);
		setMaxHeight(maxHeight);
	}

	public void init() {
		imageView.setPresenter(this);
		setImage();
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

	@Override
	public void setMaxWidth(int width) {
		imageView.setMaxWidth(width);
	}

	@Override
	public void setMaxHeight(int height) {
		imageView.setMaxHeight(height);
	}
}
