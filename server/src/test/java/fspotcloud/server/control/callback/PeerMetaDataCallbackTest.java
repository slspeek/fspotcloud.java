package fspotcloud.server.control.callback;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;

import junit.framework.TestCase;
import fspotcloud.server.control.task.actions.intern.TagImportAction;
import fspotcloud.server.model.api.PeerDatabases;
import fspotcloud.server.model.peerdatabase.PeerDatabaseDO;
import fspotcloud.shared.dashboard.actions.VoidResult;
import fspotcloud.shared.peer.rpc.actions.PeerMetaDataResult;
import fspotcloud.taskqueuedispatch.NullCallback;
import fspotcloud.taskqueuedispatch.TaskQueueDispatch;

@SuppressWarnings("unused")
public class PeerMetaDataCallbackTest extends TestCase {

	PeerDatabaseDO pd;
	PeerDatabases peerDatabases;
	TaskQueueDispatch dispatchAsync;
	PeerMetaDataCallback callback;
	PeerMetaDataResult firstResult;
	PeerMetaDataResult secondResult;

	@Override
	protected void setUp() throws Exception {
		peerDatabases = mock(PeerDatabases.class);
		pd = new PeerDatabaseDO();
		dispatchAsync = mock(TaskQueueDispatch.class);
		callback = new PeerMetaDataCallback(peerDatabases, dispatchAsync);
		firstResult = new PeerMetaDataResult(10, 10);
		secondResult = new PeerMetaDataResult(10, 21);
		super.setUp();
	}

	public void testSerialize() throws Exception {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ObjectOutputStream out = new ObjectOutputStream(bos);
		out.writeObject(callback);
		out.close();
	}

	public void testFirstResult() {
		when(peerDatabases.get()).thenReturn(pd);
		callback.onSuccess(firstResult);
		verify(peerDatabases).save(pd);
		verify(dispatchAsync).execute(new TagImportAction(0, 10), new NullCallback<VoidResult>());
	}

	
}
