package fspotcloud.server.control.task.tagimport;

import static org.mockito.Mockito.*;

import java.util.List;

import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;

import fspotcloud.botdispatch.controller.dispatch.ControllerDispatchAsync;
import fspotcloud.server.control.callback.TagDataCallback;
import fspotcloud.server.control.task.actions.intern.TagImportAction;
import fspotcloud.shared.peer.rpc.actions.GetTagData;
import fspotcloud.taskqueuedispatch.TaskQueueDispatch;
import fspotcloud.test.MockitoTestCase;
public class TagImportHandlerTest extends MockitoTestCase {

	@Mock TaskQueueDispatch recursiveCall;
	int MAX_DATA_TICKS = 2;
	@Mock ControllerDispatchAsync dispatch;
	@Captor ArgumentCaptor<TagImportAction> nextCallCaptor;
	@Captor ArgumentCaptor<GetTagData> scheduledActionCaptor;
	@Captor ArgumentCaptor<TagDataCallback> callbackCaptor;
	TagImportAction action = new TagImportAction(0, 10);
	TagImportHandler handler;
	protected void setUp() throws Exception {
		super.setUp();
		handler = new TagImportHandler(dispatch, recursiveCall, MAX_DATA_TICKS);
	}

	public void testExecute() {
		handler.execute(action, null);
		verify(recursiveCall).execute(nextCallCaptor.capture());
		TagImportAction next = nextCallCaptor.getValue();
		assertEquals(4, next.getOffset());
		assertEquals(6, next.getLimit());
		verify(dispatch, times(2)).execute(scheduledActionCaptor.capture(), callbackCaptor.capture());
		List<GetTagData> scheduled = scheduledActionCaptor.getAllValues();
		assertEquals(2, scheduled.size());
		GetTagData dataR1 = scheduled.get(0);
		GetTagData dataR2 = scheduled.get(1);
		assertEquals(0, dataR1.getOffset());
		assertEquals(2, dataR2.getOffset());
	}
}
