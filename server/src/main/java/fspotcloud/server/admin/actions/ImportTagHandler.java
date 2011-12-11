package fspotcloud.server.admin.actions;

import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.server.SimpleActionHandler;
import net.customware.gwt.dispatch.shared.Action;
import net.customware.gwt.dispatch.shared.ActionException;
import net.customware.gwt.dispatch.shared.DispatchException;

import com.google.inject.Inject;

import fspotcloud.server.control.task.actions.intern.PhotoImportAction;
import fspotcloud.server.model.api.Tag;
import fspotcloud.server.model.api.Tags;
import fspotcloud.shared.dashboard.actions.ImportTag;
import fspotcloud.shared.dashboard.actions.VoidResult;
import fspotcloud.taskqueuedispatch.NullCallback;
import fspotcloud.taskqueuedispatch.TaskQueueDispatch;

public class ImportTagHandler extends
		SimpleActionHandler<ImportTag, VoidResult> {

	final private Tags tagManager;
	final private TaskQueueDispatch dispatchAsync;

	@Inject
	public ImportTagHandler(Tags tagManager, TaskQueueDispatch dispatchAsync) {
		super();
		this.tagManager = tagManager;
		this.dispatchAsync = dispatchAsync;
	}

	@Override
	public VoidResult execute(ImportTag action, ExecutionContext context)
			throws DispatchException {
		try {
			String tagId = action.getTagId();
			Tag tag = tagManager.getById(tagId);
			if (!tag.isImportIssued()) {
				tag.setImportIssued(true);
				tagManager.save(tag);
			}
			Action<VoidResult> internAction = new PhotoImportAction(
					tagId, tag.getCachedPhotoList(), action.getPreviousCount(),
					tag.getCount() - action.getPreviousCount());
			dispatchAsync.execute(internAction, new NullCallback<VoidResult>());

		} catch (Exception e) {
			throw new ActionException(e);
		}
		return new VoidResult();
	}

}