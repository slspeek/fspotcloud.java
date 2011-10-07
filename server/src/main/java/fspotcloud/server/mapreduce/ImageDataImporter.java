package fspotcloud.server.mapreduce;

import java.util.List;

import com.google.common.collect.ImmutableList;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

import fspotcloud.botdispatch.controller.dispatch.ControllerDispatchAsync;
import fspotcloud.server.control.callback.ImageDataCallback;
import fspotcloud.server.model.api.Photo;
import fspotcloud.shared.peer.rpc.actions.GetImageData;

public class ImageDataImporter {

	final int THUMB_WIDTH;
	final int THUMB_HEIGHT;
	final int IMAGE_WIDTH;
	final int IMAGE_HEIGHT;

	final private Photo photo;
	final private ImmutableList<String> tagsForImport;
	final private ControllerDispatchAsync dispatch;

	@Inject
	public ImageDataImporter(@Assisted Photo photo,
			@Assisted ImmutableList<String> tagsForImport,
			@Assisted("thumb") String thumbDimension, @Assisted("image") String imageDimension,
			ControllerDispatchAsync dispatch) {
		super();
		this.photo = photo;
		this.tagsForImport = tagsForImport;
		this.dispatch = dispatch;
		THUMB_WIDTH = Integer.valueOf(thumbDimension.split("x")[0]);
		THUMB_HEIGHT = Integer.valueOf(thumbDimension.split("x")[1]);
		IMAGE_WIDTH = Integer.valueOf(imageDimension.split("x")[0]);
		IMAGE_HEIGHT = Integer.valueOf(imageDimension.split("x")[1]);
	}

	public void schedule(int IMAGE_TYPE) {
		if (IMAGE_TYPE == Photo.IMAGE_TYPE_THUMB) {
			scheduleThumb();
		} else if (IMAGE_TYPE == Photo.IMAGE_TYPE_BIG) {
			scheduleImage();
		} else {
			throw new IllegalArgumentException("Unkown IMAGE_TYPE");
		}
	}

	private boolean isRelevant(Photo photo) {
		List<String> photoTags = photo.getTagList();
		for (String wantedTag : tagsForImport) {
			if (photoTags != null && photoTags.contains(wantedTag)) {
				return true;
			}
		}
		return false;
	}

	private void scheduleImage() {
		if (!photo.isImageLoaded() && isRelevant(photo)) {
			GetImageData action = new GetImageData(photo.getId(), IMAGE_WIDTH, IMAGE_HEIGHT,Photo.IMAGE_TYPE_BIG );
			ImageDataCallback callback = new ImageDataCallback(Photo.IMAGE_TYPE_BIG, photo.getId());
			dispatch.execute(action, callback);
		}
	}

	private void scheduleThumb() {
		if (!photo.isThumbLoaded() && isRelevant(photo)) {
			GetImageData action = new GetImageData(photo.getId(), THUMB_WIDTH, THUMB_HEIGHT,Photo.IMAGE_TYPE_THUMB );
			ImageDataCallback callback = new ImageDataCallback(Photo.IMAGE_TYPE_THUMB, photo.getId());
			dispatch.execute(action, callback);
		}
	}
}
