package fspotcloud.server.control.callback;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;

import junit.framework.TestCase;
import fspotcloud.server.control.task.DataScheduler;
import fspotcloud.server.control.task.DataSchedulerFactory;
import fspotcloud.server.model.api.PeerDatabases;
import fspotcloud.server.model.peerdatabase.PeerDatabaseDO;
import fspotcloud.shared.peer.rpc.actions.PeerMetaDataResult;
@SuppressWarnings("unused")
public class PeerMetaDataCallbackTest extends TestCase {

	PeerDatabaseDO pd;
	PeerDatabases peerDatabases;
	DataSchedulerFactory factory;
	DataScheduler tagScheduler;
	DataScheduler photoScheduler;
	PeerMetaDataCallback callback;
	PeerMetaDataResult firstResult;
	PeerMetaDataResult secondResult;

	@Override
	protected void setUp() throws Exception {
		peerDatabases = mock(PeerDatabases.class);
		factory = mock(DataSchedulerFactory.class);
		pd = new PeerDatabaseDO();
		callback = new PeerMetaDataCallback(peerDatabases, factory);
		tagScheduler = mock(DataScheduler.class, "Tag");
		photoScheduler = mock(DataScheduler.class, "Photo");
		firstResult = new PeerMetaDataResult(10,10);
		secondResult = new PeerMetaDataResult(10,21);
		super.setUp();
	}
	
	
	public void testSerialize() throws Exception {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ObjectOutputStream out = new ObjectOutputStream(bos);
		out.writeObject(callback);
		out.close();
	}
	
	public void testFirstResult() {
		when(factory.get("Photo")).thenReturn(photoScheduler);
		when(factory.get("Tag")).thenReturn(tagScheduler);
		when(peerDatabases.get()).thenReturn(pd);
		
		
		callback.onSuccess(firstResult);
		verify(peerDatabases).save(pd);
		verify(photoScheduler).scheduleDataImport(0, 10);
		verify(tagScheduler).scheduleDataImport(0, 10);
		
		
			}
	
	public void testSecondResult() {
		pd = new PeerDatabaseDO();
		pd.setTagCount(10);
		pd.setPeerPhotoCount(10);
		when(factory.get("Photo")).thenReturn(photoScheduler);
		when(factory.get("Tag")).thenReturn(tagScheduler);
		when(peerDatabases.get()).thenReturn(pd);
		callback.onSuccess(secondResult);
		verify(photoScheduler).scheduleDataImport(10, 11);
		verify(tagScheduler).scheduleDataImport(0, 10);

		verify(peerDatabases).save(pd);
		verify(peerDatabases).save(pd);
	}
	
}
