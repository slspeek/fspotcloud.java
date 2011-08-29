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
import fspotcloud.shared.dashboard.actions.CountPhotos;
import fspotcloud.shared.dashboard.actions.VoidResult;

public class CountPhotosHandler extends
		SimpleActionHandler<CountPhotos, VoidResult> {

	final private Queue queue;

	@Inject
	public CountPhotosHandler(@Named("default") Queue queue) {
		super();
		this.queue = queue;
	}

	@Override
	public VoidResult execute(CountPhotos action, ExecutionContext context)
			throws DispatchException {
		try {
			TaskOptions task = MapReduceUtil.buildStartJob(
					"Entity Counter Mapper", "PhotoDO");
			queue.add(task);
		} catch (Exception e) {
			throw new ActionException(e);
		}
		return new VoidResult();
	}

}