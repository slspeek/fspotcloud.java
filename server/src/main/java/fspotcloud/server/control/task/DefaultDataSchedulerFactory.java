package fspotcloud.server.control.task;

import com.google.inject.Inject;

public class DefaultDataSchedulerFactory implements DataSchedulerFactory {

	final private DataSchedulerAssistedFactory schedulerFactory;
	final private DelayedDataSchedulerFactory delayedDataSchedulerFactory;

	@Inject
	public DefaultDataSchedulerFactory(
			DataSchedulerAssistedFactory schedulerFactory,
			DelayedDataSchedulerFactory delayedDataSchedulerFactory) {
		super();
		this.schedulerFactory = schedulerFactory;
		this.delayedDataSchedulerFactory = delayedDataSchedulerFactory;
	}

	public DataScheduler get(String kind) {
		DataScheduler delayed = delayedDataSchedulerFactory.get(kind);
		DataScheduler result = schedulerFactory.get(kind, delayed);
		return result;
	}

}
