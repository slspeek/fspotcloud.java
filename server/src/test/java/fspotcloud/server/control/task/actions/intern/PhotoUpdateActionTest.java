package fspotcloud.server.control.task.actions.intern;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.AssertJUnit;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.SerializationUtils;

import fspotcloud.shared.peer.rpc.actions.PhotoUpdate;

public class PhotoUpdateActionTest {

	
	PhotoUpdateAction action;
	@BeforeMethod
	protected void setUp() throws Exception {
		PhotoUpdate update = new PhotoUpdate("1");
		List<PhotoUpdate> list = new ArrayList<PhotoUpdate>();
		list.add(update);
		action = new PhotoUpdateAction(list); 
	}

	@Test
	public void testGetUpdates() {
		AssertJUnit.assertEquals(1,action.getUpdates().size());
	}
	
	
	@Test
	public void testSerialize() {
		SerializationUtils.serialize(action);
	}

}
