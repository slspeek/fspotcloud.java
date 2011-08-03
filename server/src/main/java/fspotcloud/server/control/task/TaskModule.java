package fspotcloud.server.control.task;

import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryModuleBuilder;

public class TaskModule extends AbstractModule {

	@Override
	protected void configure() {
		install(new FactoryModuleBuilder().implement(DataScheduler.class,
				DataSchedulerImpl.class).build(DataSchedulerAssistedFactory.class));
		install(new FactoryModuleBuilder().build(DelayedDataSchedulerFactory.class));
		bind(DataSchedulerFactory.class).to(DefaultDataSchedulerFactory.class);
	}

}
