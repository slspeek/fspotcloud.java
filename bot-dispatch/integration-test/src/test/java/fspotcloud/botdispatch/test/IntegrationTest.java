package fspotcloud.botdispatch.test;

import static org.mockito.Mockito.*;

import com.google.inject.Guice;
import com.google.inject.Injector;

import fspotcloud.botdispatch.bot.Bot;
import fspotcloud.botdispatch.controller.dispatch.ControllerDispatchAsync;
import fspotcloud.botdispatch.controller.inject.ControllerModule;
import fspotcloud.botdispatch.model.CommandModelModule;
import fspotcloud.botdispatch.model.DatastoreTest;
public class IntegrationTest extends DatastoreTest {

	Injector injector;

	TestAction action = new TestAction("Your name here");
	SecondAction secondAction = new SecondAction("gnu");
	TestAsyncCallback callback = new TestAsyncCallback();
	String resultMessage = "Hello to you, Your name here";
	HeavyReport report;
	ControllerDispatchAsync dispatch;
	Bot bot;

	@Override
	public void setUp() {
		report = mock(HeavyReport.class);
		 
		injector = Guice.createInjector(new LocalBotModule(),
				new ActionsModule(), new CommandModelModule(),
				new ControllerModule(), new HeavyReportModule(report));
		bot = injector.getInstance(Bot.class);
		dispatch = injector.getInstance(ControllerDispatchAsync.class);
		super.setUp();
	}

	public void testOne() {
		dispatch.execute(action, callback);
		dispatch.execute(secondAction, callback);
		bot.runForever(3);
		verify(report).report(resultMessage);
		verify(report).report("GNU");
		
	}
}
