package fspotcloud.botdispatch.controller.callback;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import junit.framework.TestCase;

import com.google.inject.Guice;
import com.google.inject.Injector;

import fspotcloud.botdispatch.model.api.Command;
import fspotcloud.botdispatch.model.api.Commands;
import fspotcloud.botdispatch.model.command.CommandDO;
import fspotcloud.botdispatch.test.HeavyReport;
import fspotcloud.botdispatch.test.HeavyReportModule;
import fspotcloud.botdispatch.test.TestAsyncCallback;
import fspotcloud.botdispatch.test.ThrowingAction;

public class ErrorHandlerImplTest extends TestCase {

	Command cmd;
	TestAsyncCallback callback;
	Throwable caught;
	ThrowingAction action;
	ErrorHandlerImpl target;
	Injector injector;
	HeavyReport report;
	Commands commandManager;

	@Override
	protected void setUp() throws Exception {
		report = mock(HeavyReport.class);
		commandManager = mock(Commands.class);
		injector = Guice.createInjector(new HeavyReportModule(report));
		action = new ThrowingAction("Demian");
		caught = new Throwable();
		callback = new TestAsyncCallback();
		cmd = new CommandDO(action, callback);
		target = new ErrorHandlerImpl(caught, cmd, injector, commandManager);
		super.setUp();
	}

	public void testOnError() {
		target.onError();
		verify(report).error(caught);
	}

}
