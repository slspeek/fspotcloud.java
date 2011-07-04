package fspotcloud.server.mapreduce;

import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.ImmutableList;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

import fspotcloud.server.control.SchedulerInterface;
import fspotcloud.server.model.api.PeerDatabases;
import fspotcloud.server.model.api.Photo;

public class ImageDataImporter {

	final String THUMB_WIDTH;
	final String THUMB_HEIGHT;
	final String IMAGE_WIDTH;
	final String IMAGE_HEIGHT;

	final private Photo photo;
	final private ImmutableList<String> tagsForImport;
	final private SchedulerInterface scheduler;

	@Inject
	public ImageDataImporter(@Assisted Photo photo,
			@Assisted ImmutableList<String> tagsForImport,
			@Assisted("thumb") String thumbDimension, @Assisted("image") String imageDimension,
			SchedulerInterface scheduler) {
		super();
		this.photo = photo;
		this.tagsForImport = tagsForImport;
		this.scheduler = scheduler;
		THUMB_WIDTH = thumbDimension.split("x")[0];
		THUMB_HEIGHT = thumbDimension.split("x")[1];
		IMAGE_WIDTH = imageDimension.split("x")[0];
		IMAGE_HEIGHT = imageDimension.split("x")[1];
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
			List<String> args = new ArrayList<String>();
			args.add(photo.getId());
			args.add(String.valueOf(IMAGE_WIDTH));
			args.add(String.valueOf(IMAGE_HEIGHT));
			args.add(String.valueOf(Photo.IMAGE_TYPE_BIG));
			scheduler.schedule("sendImageData", args);
		}
	}

	private void scheduleThumb() {
		if (!photo.isThumbLoaded() && isRelevant(photo)) {
			List<String> args = new ArrayList<String>();
			args.add(photo.getId());
			args.add(String.valueOf(THUMB_WIDTH));
			args.add(String.valueOf(THUMB_HEIGHT));
			args.add(String.valueOf(Photo.IMAGE_TYPE_THUMB));
			scheduler.schedule("sendImageData", args);
		}
	}
}
