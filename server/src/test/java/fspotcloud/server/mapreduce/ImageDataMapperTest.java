package fspotcloud.server.mapreduce;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import junit.framework.TestSuite;

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

import fspotcloud.server.model.CloudcoverDatastoreTest;

public class ImageDataMapperTest extends CloudcoverDatastoreTest {
	public static TestSuite suite() {
		return new TestSuite(ImageDataMapperTest.class);
	}
	ImageDataMapper mapper;
	
	public void testImageDataMapper() {
		mapper = new ImageDataMapper();
		assertNotNull(mapper);
	}

	public void testTaskSetupContext() throws IOException, InterruptedException {
		testImageDataMapper();
		mapper.taskSetup(null);
	}

	public void testMapKeyEntityContext() throws IOException, InterruptedException {
		testTaskSetupContext();
		Key key = KeyFactory.createKey("Photo", "1001");
		Entity entity = new Entity(key);
		entity.setProperty("description", "");
		entity.setProperty("date", new Date(0));
		entity.setProperty("tagList", new ArrayList<String>());
		entity.setProperty("fullsizeLoaded", false);
		entity.setProperty("imageLoaded", false);
		entity.setProperty("thumbLoaded", false);
		
		mapper.map(key, entity, null);
	}

}
