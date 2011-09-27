package fspotcloud.botdispatch.controller.dispatch;

import static org.mockito.Mockito.mock;
import junit.framework.TestCase;

import com.google.gwt.user.client.rpc.AsyncCallback;

import fspotcloud.botdispatch.bot.TestAction;
import fspotcloud.botdispatch.bot.TestResult;
import fspotcloud.botdispatch.model.api.Commands;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
public class ControllerDispatchAsyncTest extends TestCase {

	Commands commandManager;
	TestAction action;
	ControllerDispatchAsync target;
	AsyncCallback<TestResult> callback;

	@Override
	protected void setUp() throws Exception {
		commandManager = mock(Commands.class);
		action = new TestAction("Jim");
		target = new ControllerDispatchAsync(commandManager);
		callback = new AsyncCallback<TestResult> () {

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onSuccess(TestResult result) {
				
				
			}
			
		};
		super.setUp();
	}
	
	public void testDispatch() throws Exception {
		target.execute(action, callback);
		
		verify(commandManager).createAndSave(action, callback);
	}
	
	
	
}
