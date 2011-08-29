package fspotcloud.server.admin.actions;

import java.util.Collections;

import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.server.SimpleActionHandler;
import net.customware.gwt.dispatch.shared.ActionException;
import net.customware.gwt.dispatch.shared.DispatchException;

import com.google.inject.Inject;

import fspotcloud.server.control.SchedulerInterface;
import fspotcloud.shared.dashboard.actions.SynchronizePeer;
import fspotcloud.shared.dashboard.actions.VoidResult;

public class SynchronizePeerHandler extends
		SimpleActionHandler<SynchronizePeer, VoidResult> {

	final private SchedulerInterface scheduler;

	@Inject
	public SynchronizePeerHandler(SchedulerInterface scheduler) {
		super();
		this.scheduler = scheduler;
	}

	@Override
	public VoidResult execute(SynchronizePeer action, ExecutionContext context)
			throws DispatchException {
		try {
			scheduler.schedule("sendMetaData", Collections.EMPTY_LIST);
		} catch (Exception e) {
			throw new ActionException(e);
		}
		return new VoidResult();
	}

}