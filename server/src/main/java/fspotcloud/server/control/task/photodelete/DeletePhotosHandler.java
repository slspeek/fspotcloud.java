package fspotcloud.server.control.task.photodelete;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.server.SimpleActionHandler;
import net.customware.gwt.dispatch.shared.DispatchException;
import fspotcloud.botdispatch.controller.dispatch.ControllerDispatchAsync;
import fspotcloud.server.control.task.actions.intern.DeletePhotos;
import fspotcloud.server.model.api.Photos;
import fspotcloud.shared.dashboard.actions.VoidResult;
import fspotcloud.taskqueuedispatch.NullCallback;
import fspotcloud.taskqueuedispatch.TaskQueueDispatch;

public class DeletePhotosHandler extends
		SimpleActionHandler<DeletePhotos, VoidResult> {

	final private int MAX_DELETE_TICKS;
	final private TaskQueueDispatch dispatchAsync;
	final private Photos photos;

	@Inject
	public DeletePhotosHandler(@Named("maxDelete") int maxDeleteTicks,
			TaskQueueDispatch dispatchAsync, Photos photos) {
		super();
		MAX_DELETE_TICKS = maxDeleteTicks;
		this.dispatchAsync = dispatchAsync;
		this.photos = photos;

	}

	@Override
	public VoidResult execute(DeletePhotos action, ExecutionContext context)
			throws DispatchException {
		Iterator<String> it = action.getKeysToBeDeleted().iterator();
		List<String> weWillDelete = new ArrayList<String>();
		for(int i = 0; i < MAX_DELETE_TICKS &&  it.hasNext(); i++ ) {
			String key  = it.next();
			weWillDelete.add(key);
			it.remove();
		}
		if (!action.getKeysToBeDeleted().isEmpty()) {
			dispatchAsync.execute(action, new NullCallback<VoidResult>());
		}
		photos.deleteAll(weWillDelete);
		return new VoidResult();
	}

}