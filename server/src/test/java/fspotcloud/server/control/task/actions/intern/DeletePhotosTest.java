package fspotcloud.server.control.task.actions.intern;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.SerializationUtils;

import fspotcloud.shared.photo.PhotoInfo;

public class DeletePhotosTest {

	@BeforeMethod
	protected void setUp() throws Exception {
	}
	
	@Test
	public void testSerialization() {
		PhotoInfo photoInfoA = new PhotoInfo("", "", new Date(10));
		List<PhotoInfo> list = new ArrayList<PhotoInfo>();
		list.add(photoInfoA);
		DeletePhotos action = new DeletePhotos("fooMock", list);
		byte[] ser = SerializationUtils.serialize(action);
		DeletePhotos deserialized = (DeletePhotos) SerializationUtils.deserialize(ser);
	}
	
	

}
