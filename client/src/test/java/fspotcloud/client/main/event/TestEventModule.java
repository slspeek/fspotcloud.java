package fspotcloud.client.main.event;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;

public class TestEventModule extends EventModule {

	@Override
	protected void configure() {
		super.configure();
		bind(EventBus.class).to(SimpleEventBus.class);
	}
}
