package fspotcloud.server.admin.actions;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import junit.framework.TestCase;
import net.customware.gwt.dispatch.shared.DispatchException;
import fspotcloud.botdispatch.model.api.Commands;
import fspotcloud.server.mapreduce.MapReduceInfo;
import fspotcloud.server.model.api.PeerDatabase;
import fspotcloud.server.model.api.PeerDatabases;
import fspotcloud.server.model.peerdatabase.PeerDatabaseDO;
import fspotcloud.shared.admin.GetMetaDataResult;
import fspotcloud.shared.dashboard.actions.GetMetaData;

public class GetMetaDataHandlerTest extends TestCase {

	GetMetaDataHandler handler;
	GetMetaData action = new GetMetaData();
	MapReduceInfo mapInfo;
	Commands commandManager;
	PeerDatabases defaultPeer;
	PeerDatabase pd;

	@Override
	protected void setUp() throws Exception {
		commandManager = mock(Commands.class);
		defaultPeer = mock(PeerDatabases.class);
		mapInfo = mock(MapReduceInfo.class);
		when(mapInfo.activeCount("Delete All Mapper")).thenReturn(2);
		pd = new PeerDatabaseDO();
		handler = new GetMetaDataHandler(commandManager, defaultPeer, mapInfo);
	}

	public void testExecute() throws DispatchException {
		when(commandManager.getCountUnderAThousend()).thenReturn(100);
		when(defaultPeer.get()).thenReturn(pd);
		GetMetaDataResult result = handler.execute(action, null);
		assertNull(result.getInstanceName());
		assertEquals(0, result.getPeerPhotoCount());
		assertEquals(100, result.getPendingCommandCount());
		assertFalse(result.isCountPhotosActive());
		assertTrue(result.isDeletePhotosActive());
		assertFalse(result.isDeleteTagsActive());
	}

	public void testException() {
		when(commandManager.getCountUnderAThousend()).thenThrow(RuntimeException.class);
		when(defaultPeer.get()).thenReturn(pd);
		try {
			GetMetaDataResult result = handler.execute(action, null);
			fail();
		} catch (DispatchException e) {
		}
	}
}
