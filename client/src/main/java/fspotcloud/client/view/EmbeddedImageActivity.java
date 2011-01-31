package fspotcloud.client.view;

import com.google.inject.Inject;

import fspotcloud.client.data.DataManager;

public class EmbeddedImageActivity extends ImageActivity {

	@Inject
	public EmbeddedImageActivity(ImageView imageView, DataManager dataManager,
			PlaceGoTo placeGoto, SlideshowTimer slideshowTimer) {
		super(imageView, dataManager, placeGoto, slideshowTimer);
	}

	@Override
	protected void goToPhoto(String tagId, String photoId) {
		placeGoTo.goTo(new TagViewingPlace(tagId, photoId));
	}
}
