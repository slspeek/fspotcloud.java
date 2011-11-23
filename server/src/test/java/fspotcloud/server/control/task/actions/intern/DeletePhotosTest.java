package fspotcloud.server.control.task.actions.intern;

import java.util.List;

import org.apache.commons.lang.SerializationUtils;

import junit.framework.TestCase;

import com.google.appengine.repackaged.com.google.common.collect.ImmutableList;

public class DeletePhotosTest extends TestCase {

	protected void setUp() throws Exception {
		super.setUp();
	}
	
	public void testSerialization() {
		List<String> keys = ImmutableList.of("1", "2");
		DeletePhotos action = new DeletePhotos(keys);
		byte[] ser = SerializationUtils.serialize(action);
		DeletePhotos deserialized = (DeletePhotos) SerializationUtils.deserialize(ser);
		assertEquals(action, deserialized);
	}
	
	

}
