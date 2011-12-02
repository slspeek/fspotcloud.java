package fspotcloud.server.control.task.actions.intern;

import junit.framework.TestCase;

import org.apache.commons.lang.SerializationUtils;

public class DeletePhotosTest extends TestCase {

	protected void setUp() throws Exception {
		super.setUp();
	}
	
	public void testSerialization() {
		DeletePhotos action = new DeletePhotos("foo");
		byte[] ser = SerializationUtils.serialize(action);
		DeletePhotos deserialized = (DeletePhotos) SerializationUtils.deserialize(ser);
	}
	
	

}
