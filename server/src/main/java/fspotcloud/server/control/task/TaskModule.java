package fspotcloud.server.control.task;

import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryModuleBuilder;
import com.google.inject.name.Names;

import fspotcloud.server.control.task.tagimport.DataScheduler;
import fspotcloud.server.control.task.tagimport.DataSchedulerAssistedFactory;
import fspotcloud.server.control.task.tagimport.DataSchedulerFactory;
import fspotcloud.server.control.task.tagimport.DefaultDataScheduler;
import fspotcloud.server.control.task.tagimport.DefaultDataSchedulerFactory;
import fspotcloud.server.control.task.tagimport.DelayedDataSchedulerFactory;

public class TaskModule extends AbstractModule {

	@Override
	protected void configure() {
		install(new FactoryModuleBuilder().implement(DataScheduler.class,
				DefaultDataScheduler.class).build(
				DataSchedulerAssistedFactory.class));
		install(new FactoryModuleBuilder()
				.build(DelayedDataSchedulerFactory.class));
		bind(DataSchedulerFactory.class).to(DefaultDataSchedulerFactory.class);
		
		bind(String.class).annotatedWith(Names.named("imageDimension")).toInstance("1024x768");
		bind(String.class).annotatedWith(Names.named("thumbDimension")).toInstance("512x384");
		bind(Integer.class).annotatedWith(Names.named("maxPhotoTicks")).toInstance(8);
	}

}
