package fspotcloud.server.control.task.actions.intern;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.SerializationUtils;

import fspotcloud.shared.peer.rpc.actions.PhotoUpdate;

import junit.framework.TestCase;

public class PhotoUpdateActionTest extends TestCase {

	
	PhotoUpdateAction action;
	protected void setUp() throws Exception {
		super.setUp();
		PhotoUpdate update = new PhotoUpdate("1");
		List<PhotoUpdate> list = new ArrayList<PhotoUpdate>();
		list.add(update);
		action = new PhotoUpdateAction(list); 
	}

	public void testGetUpdates() {
		assertEquals(1,action.getUpdates().size());
	}
	
	
	public void testSerialize() {
		SerializationUtils.serialize(action);
	}

}
