package fspotcloud.server.control.callback;

import java.io.Serializable;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;

import fspotcloud.server.control.task.actions.intern.PhotoUpdateAction;
import fspotcloud.server.control.task.actions.intern.RemovePhotosFromTagAction;
import fspotcloud.shared.peer.rpc.actions.TagUpdateInstructionsResult;
import fspotcloud.taskqueuedispatch.TaskQueueDispatch;

public class TagUpdateInstructionsCallback implements
		AsyncCallback<TagUpdateInstructionsResult>, Serializable {
	private static final long serialVersionUID = -6213572441944313878L;
	@Inject
	transient private TaskQueueDispatch dispatchAsync;
	
	private String tagId;

	public TagUpdateInstructionsCallback(String tagId, TaskQueueDispatch dispatchAsync) {
		super();
		this.dispatchAsync = dispatchAsync;
		this.tagId = tagId;
	}

	@Override
	public void onFailure(Throwable caught) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onSuccess(TagUpdateInstructionsResult result) {
		PhotoUpdateAction photoUpdate = new PhotoUpdateAction(
				result.getToBoUpdated());
		RemovePhotosFromTagAction photoRemove = new RemovePhotosFromTagAction(tagId, result.getToBoRemovedFromTag());
		dispatchAsync.execute(photoRemove);
		dispatchAsync.execute(photoUpdate);
	}

}
