package fspotcloud.server.admin.actions;

import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.server.SimpleActionHandler;
import net.customware.gwt.dispatch.shared.ActionException;
import net.customware.gwt.dispatch.shared.DispatchException;

import com.google.appengine.api.taskqueue.Queue;
import com.google.appengine.api.taskqueue.TaskOptions;
import com.google.inject.Inject;
import com.google.inject.name.Named;

import fspotcloud.server.mapreduce.MapReduceUtil;
import fspotcloud.shared.dashboard.actions.DeleteAllPhotos;
import fspotcloud.shared.dashboard.actions.VoidResult;

public class DeleteAllPhotosHandler extends
		SimpleActionHandler<DeleteAllPhotos, VoidResult> {

	final private Queue queue;

	@Inject
	public DeleteAllPhotosHandler(@Named("default") Queue queue) {
		super();
		this.queue = queue;
	}

	@Override
	public VoidResult execute(DeleteAllPhotos action, ExecutionContext context)
			throws DispatchException {
		try {
			TaskOptions task = MapReduceUtil.buildStartJob("Delete All Mapper",
					"PhotoDO");
			queue.add(task);
		} catch (Exception e) {
			throw new ActionException(e);
		}
		return new VoidResult();
	}

}