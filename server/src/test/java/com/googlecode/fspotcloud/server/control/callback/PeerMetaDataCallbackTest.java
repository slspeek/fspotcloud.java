package com.googlecode.fspotcloud.server.control.callback;

import com.googlecode.fspotcloud.model.jpa.peerdatabase.PeerDatabaseEntity;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;

import com.googlecode.fspotcloud.server.control.task.actions.intern.TagImportAction;
import com.googlecode.fspotcloud.server.model.api.PeerDatabase;
import com.googlecode.fspotcloud.server.model.api.PeerDatabases;
import com.googlecode.fspotcloud.shared.dashboard.actions.VoidResult;
import com.googlecode.fspotcloud.shared.peer.rpc.actions.PeerMetaDataResult;
import com.googlecode.taskqueuedispatch.NullCallback;
import com.googlecode.taskqueuedispatch.TaskQueueDispatch;

@SuppressWarnings("unused")
public class PeerMetaDataCallbackTest {

	PeerDatabase pd;
	PeerDatabases peerDatabases;
	TaskQueueDispatch dispatchAsync;
	PeerMetaDataCallback callback;
	PeerMetaDataResult firstResult;
	PeerMetaDataResult secondResult;

	@BeforeMethod
	protected void setUp() throws Exception {
		peerDatabases = mock(PeerDatabases.class);
		pd = new PeerDatabaseEntity();
		dispatchAsync = mock(TaskQueueDispatch.class);
		callback = new PeerMetaDataCallback(peerDatabases, dispatchAsync);
		firstResult = new PeerMetaDataResult(10, 10);
		secondResult = new PeerMetaDataResult(10, 21);
	}

	@Test
	public void testSerialize() throws Exception {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ObjectOutputStream out = new ObjectOutputStream(bos);
		out.writeObject(callback);
		out.close();
	}

	@Test
	public void testFirstResult() {
		when(peerDatabases.get()).thenReturn(pd);
		callback.onSuccess(firstResult);
		verify(peerDatabases).save(pd);
		verify(dispatchAsync).execute(new TagImportAction(0, 10), new NullCallback<VoidResult>());
	}

	
}
