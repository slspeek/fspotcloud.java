package fspotcloud.client.main.view.api;

import com.google.inject.assistedinject.Assisted;

import fspotcloud.shared.photo.PhotoInfo;

public interface ImagePresenterFactory {

	ImageView.ImagePresenter get(@Assisted String tagId,
			@Assisted PhotoInfo info, @Assisted ImageView imageView,
			@Assisted boolean thumb);
}
