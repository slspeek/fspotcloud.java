package fspotcloud.client.main.view.api;

import com.google.inject.assistedinject.Assisted;

import fspotcloud.client.place.BasePlace;

public interface ImagePresenterFactory {

	ImageView.ImagePresenter get(@Assisted("maxWidth") int maxWidth,
			@Assisted("maxHeight") int maxHeight, @Assisted BasePlace place,
			@Assisted ImageView imageView, @Assisted boolean thumb);
}
