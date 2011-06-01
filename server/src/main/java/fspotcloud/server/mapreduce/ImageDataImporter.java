package fspotcloud.server.mapreduce;

import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.ImmutableList;

import fspotcloud.server.control.SchedulerInterface;
import fspotcloud.server.model.api.Photo;

public class ImageDataImporter {

	final static  int THUMB_WIDTH = 100;
	final static  int THUMB_HEIGHT = 75;
	final static  int IMAGE_WIDTH = 1024;
	final static  int IMAGE_HEIGHT = 768;

	final private Photo photo;
	final private ImmutableList<String> tagsForImport;
	final private SchedulerInterface scheduler;
	
	public ImageDataImporter(Photo photo, ImmutableList<String> tagsForImport,
			SchedulerInterface scheduler) {
		super();
		this.photo = photo;
		this.tagsForImport = tagsForImport;
		this.scheduler = scheduler;
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
		for (String wantedTag: tagsForImport) {
			if (photoTags.contains(wantedTag)) {
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
