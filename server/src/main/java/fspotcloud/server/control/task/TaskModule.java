package fspotcloud.server.control.task;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;

public class TaskModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(String.class).annotatedWith(Names.named("imageDimension")).toInstance("1024x768");
		bind(String.class).annotatedWith(Names.named("thumbDimension")).toInstance("512x384");
		bind(Integer.class).annotatedWith(Names.named("maxPhotoTicks")).toInstance(8);
	}

}
