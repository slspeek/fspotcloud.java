package fspotcloud.server.control.task.photoupdate;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.inject.Named;

import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.server.SimpleActionHandler;
import net.customware.gwt.dispatch.shared.DispatchException;
import fspotcloud.botdispatch.controller.dispatch.ControllerDispatchAsync;
import fspotcloud.server.control.callback.PhotoDataCallback;
import fspotcloud.server.control.task.actions.intern.PhotoUpdateAction;
import fspotcloud.shared.dashboard.actions.VoidResult;
import fspotcloud.shared.peer.rpc.actions.GetPhotoData;
import fspotcloud.shared.peer.rpc.actions.ImageSpecs;
import fspotcloud.shared.peer.rpc.actions.PhotoUpdate;
import fspotcloud.taskqueuedispatch.TaskQueueDispatch;

public class PhotoUpdateHandler extends
		SimpleActionHandler<PhotoUpdateAction, VoidResult> {
	@SuppressWarnings("unused")
	final static private Logger log = Logger.getLogger(PhotoUpdateHandler.class
			.getName());
	final private int MAX_DATA_TICKS;
	final private int MAX_PHOTO_TICKS;

	final private ControllerDispatchAsync controllerDispatch;
	final private TaskQueueDispatch dispatchAsync;
	final private ImageSpecs imageSpecs;

	@Inject
	public PhotoUpdateHandler(@Named("maxTicks") int maxTicks,
			@Named("maxPhotoTicks") int maxPhotoTicks,
			@Named("defaultImageSpecs") ImageSpecs imageSpecs,
			ControllerDispatchAsync controllerDispatch,
			TaskQueueDispatch dispatchAsync) {
		super();
		this.controllerDispatch = controllerDispatch;
		this.dispatchAsync = dispatchAsync;
		MAX_DATA_TICKS = maxTicks;
		MAX_PHOTO_TICKS = maxPhotoTicks;
		this.imageSpecs = imageSpecs;
	}

	@Override
	public VoidResult execute(PhotoUpdateAction action, ExecutionContext context)
			throws DispatchException {
		List<PhotoUpdate> updates = action.getUpdates();
		int size = updates.size();
		int countWeWillDo;
		int needToScheduleCount = (int) Math.ceil((double) size
				/ (double) MAX_PHOTO_TICKS);
		if (needToScheduleCount > MAX_DATA_TICKS) {
			countWeWillDo = MAX_DATA_TICKS;
			List<PhotoUpdate> nextList = new ArrayList<PhotoUpdate>();
			nextList.addAll(updates.subList(
					countWeWillDo * MAX_PHOTO_TICKS, size));
			dispatchAsync.execute(new PhotoUpdateAction(nextList));
		} else {
			countWeWillDo = needToScheduleCount;
		}
		// Do our part of the job, scheduling the head
		for (int i = 0; i < countWeWillDo; i++) {
			int beginning = i * MAX_PHOTO_TICKS;
			List<String> imageKeys = new ArrayList<String>();
			for (int j = beginning; j < MAX_PHOTO_TICKS + beginning
					&& j < updates.size(); j++) {

				PhotoUpdate photoUpdate = updates.get(j);
				imageKeys.add(photoUpdate.getPhotoId());
			}
			// log.info("Doing our part " + imageKeys);
			GetPhotoData botAction = new GetPhotoData(imageSpecs, imageKeys);
			PhotoDataCallback callback = new PhotoDataCallback(null, null, null);
			controllerDispatch.execute(botAction, callback);
		}
		return new VoidResult();
	}

}