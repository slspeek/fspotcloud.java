package fspotcloud.server.admin.actions;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.Assert;
import org.testng.AssertJUnit;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import net.customware.gwt.dispatch.shared.DispatchException;
import fspotcloud.botdispatch.model.api.Commands;
import fspotcloud.server.model.api.PeerDatabase;
import fspotcloud.server.model.api.PeerDatabases;
import fspotcloud.server.model.peerdatabase.PeerDatabaseDO;
import fspotcloud.shared.admin.GetMetaDataResult;
import fspotcloud.shared.dashboard.actions.GetMetaData;
import fspotcloud.test.MockitoTestCase;
import static org.testng.AssertJUnit.*;
import static org.mockito.Mockito.*;
public class GetMetaDataHandlerTest  {

	GetMetaDataHandler handler;
	GetMetaData action = new GetMetaData();
	@Mock
	Commands commandManager;
	@Mock
	PeerDatabases defaultPeer;
	PeerDatabase pd;

	@BeforeMethod
	protected void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		pd = new PeerDatabaseDO();
		handler = new GetMetaDataHandler(commandManager, defaultPeer);
	}

	@Test
	public void testExecute() throws DispatchException {
		when(commandManager.getCountUnderAThousend()).thenReturn(100);
		when(defaultPeer.get()).thenReturn(pd);
		GetMetaDataResult result = handler.execute(action, null);
		assertNull(result.getInstanceName());
		AssertJUnit.assertEquals(0, result.getPeerPhotoCount());
		AssertJUnit.assertEquals(100, result.getPendingCommandCount());
		
	}

	@Test
	public void testException() {
		when(commandManager.getCountUnderAThousend()).thenThrow(RuntimeException.class);
		when(defaultPeer.get()).thenReturn(pd);
		try {
			GetMetaDataResult result = handler.execute(action, null);
			Assert.fail();
		} catch (DispatchException e) {
		}
	}
}
