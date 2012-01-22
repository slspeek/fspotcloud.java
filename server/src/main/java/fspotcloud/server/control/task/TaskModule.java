package fspotcloud.server.control.task;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;

import fspotcloud.shared.peer.rpc.actions.ImageSpecs;

public class TaskModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(ImageSpecs.class).annotatedWith(Names.named("defaultImageSpecs"))
				.toInstance(new ImageSpecs(1024, 768, 512, 378));
		bind(Integer.class).annotatedWith(Names.named("maxPhotoTicks"))
				.toInstance(7);
	}

}
