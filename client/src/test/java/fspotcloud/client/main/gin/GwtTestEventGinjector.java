package fspotcloud.client.main.gin;

import com.google.gwt.core.client.GWT;
import com.google.gwt.junit.client.GWTTestCase;
import com.google.inject.Provider;

import fspotcloud.client.main.shared.ApplicationEvent;
import fspotcloud.client.main.shared.ApplicationEventProviderFactory;

public class GwtTestEventGinjector extends GWTTestCase {

	EventGinjector injector;

	public void testInitialization() {
		injector = GWT.create(EventGinjector.class);
		assertNotNull(injector);
	}
	
	public void testProduceEvent() {
		testInitialization();
		ApplicationEvent event;
		Provider<ApplicationEvent> provider;
		ApplicationEventProviderFactory factory = injector.getApplicationEventFactory();
		provider = factory.get(ApplicationEvent.ActionType.DASHBOARD);
		event = provider.get();
		assertEquals(ApplicationEvent.ActionType.DASHBOARD, event.getActionType());
	}
	
	public void testProduceOtherEvent() {
		testInitialization();
		ApplicationEvent event;
		Provider<ApplicationEvent> provider;
		ApplicationEventProviderFactory factory = injector.getApplicationEventFactory();
		provider = factory.get(ApplicationEvent.ActionType.HELP);
		event = provider.get();
		assertEquals(ApplicationEvent.ActionType.HELP, event.getActionType());
	}

	@Override
	public String getModuleName() {

		return "fspotcloud.FSpotCloud";
	}
}
