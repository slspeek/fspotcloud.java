package com.googlecode.fspotcloud.server.admin.actions;

import static org.mockito.Mockito.*;
import static org.testng.AssertJUnit.assertNull;
import net.customware.gwt.dispatch.shared.DispatchException;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.googlecode.botdispatch.model.api.Commands;
import com.googlecode.fspotcloud.model.jpa.peerdatabase.PeerDatabaseEntity;
import com.googlecode.fspotcloud.server.model.api.PeerDatabase;
import com.googlecode.fspotcloud.server.model.api.PeerDatabases;
import com.googlecode.fspotcloud.shared.admin.GetMetaDataResult;
import com.googlecode.fspotcloud.shared.dashboard.actions.GetMetaData;
import com.googlecode.fspotcloud.user.AdminPermission;
public class GetMetaDataHandlerTest  {

	GetMetaDataHandler handler;
	GetMetaData action = new GetMetaData();
	@Mock
	Commands commandManager;
	@Mock
	PeerDatabases defaultPeer;
        @Mock 
        AdminPermission adminPermission;
	PeerDatabase pd;

	@BeforeMethod
	protected void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		pd = new PeerDatabaseEntity();
		handler = new GetMetaDataHandler(commandManager, defaultPeer, adminPermission);
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
        
        @Test(expectedExceptions=SecurityException.class) void forbidden() throws DispatchException {
            doThrow(new SecurityException()).when(adminPermission).chechAdminPermission();
            GetMetaDataResult result = handler.execute(action, null);
            
        }
}