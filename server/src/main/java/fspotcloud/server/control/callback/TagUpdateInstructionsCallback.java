package fspotcloud.server.control.callback;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;

import fspotcloud.server.control.task.actions.intern.PhotoUpdateAction;
import fspotcloud.server.control.task.actions.intern.RemovePhotosFromTagAction;
import fspotcloud.shared.peer.rpc.actions.TagUpdateInstructionsResult;
import fspotcloud.taskqueuedispatch.TaskQueueDispatch;

public class TagUpdateInstructionsCallback implements
		AsyncCallback<TagUpdateInstructionsResult>, Serializable {
	
	protected static final Logger log = Logger.getLogger(TagUpdateInstructionsCallback.class.getName());
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
		log.log(Level.SEVERE, "Caught: ", caught);
	}

	@Override
	public void onSuccess(TagUpdateInstructionsResult result) {
		PhotoUpdateAction photoUpdate = new PhotoUpdateAction(
				result.getToBoUpdated());
		RemovePhotosFromTagAction photoRemove = new RemovePhotosFromTagAction(tagId, result.getToBoRemovedFromTag());
		log.info("Before " + photoRemove);
		dispatchAsync.execute(photoRemove);
		log.info("2 Before " + photoUpdate);
		dispatchAsync.execute(photoUpdate);
	}

}
