package fspotcloud.client.view;

import com.google.gwt.place.shared.PlaceController;
import com.google.inject.Inject;

import fspotcloud.client.data.DataManager;

public class EmbeddedImageActivity extends ImageActivity {

	@Inject
	public EmbeddedImageActivity(ImageView imageView, DataManager dataManager,
			PlaceController placeController, SlideshowTimer slideshowTimer) {
		super(imageView, dataManager, placeController, slideshowTimer);
	}

	@Override
	protected void goToPhoto(String tagId, String photoId) {
		placeController.goTo(new TagViewingPlace(tagId, photoId));
	}
}
