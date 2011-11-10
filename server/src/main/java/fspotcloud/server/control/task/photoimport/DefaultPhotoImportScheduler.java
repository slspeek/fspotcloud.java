package fspotcloud.server.control.task.photoimport;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import com.google.inject.name.Named;

import fspotcloud.botdispatch.controller.dispatch.ControllerDispatchAsync;
import fspotcloud.server.control.callback.PhotoDataCallback;
import fspotcloud.server.control.callback.TagDataCallback;
import fspotcloud.shared.peer.rpc.actions.GetPhotoData;
import fspotcloud.shared.peer.rpc.actions.GetTagData;

public class DefaultPhotoImportScheduler implements PhotoImportScheduler {

	final int THUMB_WIDTH;
	final int THUMB_HEIGHT;
	final int IMAGE_WIDTH;
	final int IMAGE_HEIGHT;
	final private int MAX_DATA_TICKS;
	final private int MAX_PHOTO_TICKS;
	final private PhotoImportScheduler recursiveCall;

	final private ControllerDispatchAsync dispatch;

	@Inject
	public DefaultPhotoImportScheduler(ControllerDispatchAsync dispatch,
			@Named("maxTicks") int maxTicks,
			@Named("maxPhotoTicks") int maxPhotoTicks,
			@Named("thumbDimension") String thumbDimension,
			@Named("imageDimension") String imageDimension,
			@Named("delayedCall") PhotoImportScheduler recursiveCall) {
		super();
		this.dispatch = dispatch;
		MAX_DATA_TICKS = maxTicks;
		MAX_PHOTO_TICKS = maxPhotoTicks;
		this.recursiveCall = recursiveCall;
		THUMB_WIDTH = Integer.valueOf(thumbDimension.split("x")[0]);
		THUMB_HEIGHT = Integer.valueOf(thumbDimension.split("x")[1]);
		IMAGE_WIDTH = Integer.valueOf(imageDimension.split("x")[0]);
		IMAGE_HEIGHT = Integer.valueOf(imageDimension.split("x")[1]);
	}

	@Override
	public void schedulePhotoImport(String tagId, String minKey, int offset,
			int limit) {
		int countWeWillDo;
		int needToScheduleCount = (int) Math.ceil((double) limit
				/ (double) MAX_PHOTO_TICKS);
		if (needToScheduleCount > MAX_DATA_TICKS) {
			// Schedule the next task
			int delta = MAX_DATA_TICKS * MAX_PHOTO_TICKS;
			int nextOffset = offset + delta;
			int nextLimit = limit - delta;
			recursiveCall.schedulePhotoImport(tagId, minKey, nextOffset,
					nextLimit);
			countWeWillDo = MAX_DATA_TICKS;
		} else {
			countWeWillDo = needToScheduleCount;
		}
		// Do our part of the job, scheduling the head
		for (int i = 0; i < countWeWillDo; i++) {
			int beginning = offset + i * MAX_PHOTO_TICKS;
			GetPhotoData action = new GetPhotoData(tagId, minKey, beginning,
					MAX_PHOTO_TICKS, IMAGE_WIDTH, IMAGE_HEIGHT, THUMB_WIDTH,
					THUMB_HEIGHT);
			PhotoDataCallback callback = new PhotoDataCallback(null, null);
			dispatch.execute(action, callback);
		}
	}
}
