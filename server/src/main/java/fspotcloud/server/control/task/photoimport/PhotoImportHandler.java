package fspotcloud.server.control.task.photoimport;

import javax.inject.Inject;
import javax.inject.Named;

import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.server.SimpleActionHandler;
import net.customware.gwt.dispatch.shared.DispatchException;


import fspotcloud.botdispatch.controller.dispatch.ControllerDispatchAsync;
import fspotcloud.server.control.callback.PhotoDataCallback;
import fspotcloud.server.control.task.actions.intern.PhotoImportAction;
import fspotcloud.shared.dashboard.actions.VoidResult;
import fspotcloud.shared.peer.rpc.actions.GetPhotoData;
import fspotcloud.taskqueuedispatch.NullCallback;
import fspotcloud.taskqueuedispatch.TaskQueueDispatch;

public class PhotoImportHandler extends
		SimpleActionHandler<PhotoImportAction, VoidResult> {
	
	final int THUMB_WIDTH;
	final int THUMB_HEIGHT;
	final int IMAGE_WIDTH;
	final int IMAGE_HEIGHT;
	final private int MAX_DATA_TICKS;
	final private int MAX_PHOTO_TICKS;
	
	final private ControllerDispatchAsync controllerDispatch;
	final private TaskQueueDispatch dispatchAsync;
	
	@Inject
	public PhotoImportHandler(@Named("maxTicks") int maxTicks,
			@Named("maxPhotoTicks") int maxPhotoTicks,
			@Named("thumbDimension") String thumbDimension,
			@Named("imageDimension") String imageDimension,
			ControllerDispatchAsync controllerDispatch,
			TaskQueueDispatch dispatchAsync) {
		super();
		this.controllerDispatch = controllerDispatch;
		this.dispatchAsync = dispatchAsync;
		MAX_DATA_TICKS = maxTicks;
		MAX_PHOTO_TICKS = maxPhotoTicks;
		THUMB_WIDTH = Integer.valueOf(thumbDimension.split("x")[0]);
		THUMB_HEIGHT = Integer.valueOf(thumbDimension.split("x")[1]);
		IMAGE_WIDTH = Integer.valueOf(imageDimension.split("x")[0]);
		IMAGE_HEIGHT = Integer.valueOf(imageDimension.split("x")[1]);
	}

	@Override
	public VoidResult execute(PhotoImportAction action,
			ExecutionContext context) throws DispatchException {
		int limit = action.getLimit();
		int offset = action.getOffset();
		String tagId = action.getTagId();
		int countWeWillDo;
		int needToScheduleCount = (int) Math.ceil((double) limit
				/ (double) MAX_PHOTO_TICKS);
		if (needToScheduleCount > MAX_DATA_TICKS) {
			// Schedule the next task
			int delta = MAX_DATA_TICKS * MAX_PHOTO_TICKS;
			int nextOffset = offset + delta;
			int nextLimit = limit - delta;
			dispatchAsync.execute(new PhotoImportAction(tagId, null, nextOffset,
					nextLimit), new NullCallback());
			countWeWillDo = MAX_DATA_TICKS;
		} else {
			countWeWillDo = needToScheduleCount;
		}
		// Do our part of the job, scheduling the head
		for (int i = 0; i < countWeWillDo; i++) {
			int beginning = offset + i * MAX_PHOTO_TICKS;
			GetPhotoData botAction = new GetPhotoData(tagId, "", beginning,
					MAX_PHOTO_TICKS, IMAGE_WIDTH, IMAGE_HEIGHT, THUMB_WIDTH,
					THUMB_HEIGHT);
			PhotoDataCallback callback = new PhotoDataCallback(null, null, null);
			controllerDispatch.execute(botAction, callback);
		}
		return new VoidResult();
	}

	
	
}