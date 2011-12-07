package fspotcloud.server.control.task.actions.intern;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import junit.framework.TestCase;

import org.apache.commons.lang.SerializationUtils;

import fspotcloud.shared.photo.PhotoInfo;

public class DeletePhotosTest extends TestCase {

	protected void setUp() throws Exception {
		super.setUp();
	}
	
	public void testSerialization() {
		PhotoInfo photoInfoA = new PhotoInfo("", "", new Date(10));
		List<PhotoInfo> list = new ArrayList<PhotoInfo>();
		list.add(photoInfoA);
		DeletePhotos action = new DeletePhotos("fooMock", list);
		byte[] ser = SerializationUtils.serialize(action);
		DeletePhotos deserialized = (DeletePhotos) SerializationUtils.deserialize(ser);
	}
	
	

}
