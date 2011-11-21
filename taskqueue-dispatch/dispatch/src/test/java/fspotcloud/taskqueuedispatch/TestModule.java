package fspotcloud.taskqueuedispatch;

import java.util.ArrayList;
import java.util.List;

import com.google.inject.AbstractModule;

public class TestModule extends AbstractModule {


	@Override
	protected void configure() {
		bind(List.class).toInstance(new ArrayList<String>());
	}

}
