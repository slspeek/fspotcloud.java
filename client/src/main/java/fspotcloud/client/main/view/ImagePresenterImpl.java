package fspotcloud.client.main.view;

import java.util.logging.Logger;

import fspotcloud.client.main.view.api.ImageView;
import fspotcloud.client.place.BasePlace;

public class ImagePresenterImpl implements ImageView.ImagePresenter {
	private static final Logger log = Logger.getLogger(ImagePresenterImpl.class
			.getName());

	final private ImageView imageView;
	final private String tagId;
	final private String photoId;

	public ImagePresenterImpl(BasePlace place, ImageView imageView) {
		tagId = place.getTagId();
		photoId = place.getPhotoId();
		this.imageView = imageView;
	}

	public void init() {
		log.info("init");
		setImage();
	}

	public void setImage() {
		if (photoId != null) {
			imageView.setImageUrl("/image?id=" + photoId);
		} else {
			log.warning("No photoId defined for tagId:  " + tagId);
		}
	}
}
