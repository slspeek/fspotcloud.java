package fspotcloud.server.model.photo;

import java.util.Date;

import com.google.appengine.api.datastore.Entity;
import com.google.common.collect.ImmutableList;

import fspotcloud.server.model.DatastoreTest;
import fspotcloud.server.model.api.Photo;

public class PhotoDOBuilderTest extends DatastoreTest {

	public static final Date NOW = new Date();
	public void testCreate() {
		Entity e = new Entity("PhotoDO");
		e.setProperty("id", "1");
		e.setProperty("description", "nice");
		e.setProperty("tagList", ImmutableList.of("10", "11", "12"));
		e.setProperty("date", NOW);
		e.setProperty("imageLoaded", false);
		e.setProperty("thumbLoaded", false);
		e.setProperty("fullsizeLoaded", false);
		
		Photo photo = (new PhotoDOBuilder()).create(e);
		assertEquals("1", photo.getId());
		assertEquals("nice", photo.getDescription());
		assertEquals(ImmutableList.of("10", "11", "12"), photo.getTagList());
		assertEquals(NOW, photo.getDate());
		assertEquals(Boolean.FALSE, photo.isFullsizeLoaded());
		assertEquals(Boolean.FALSE, photo.isImageLoaded());
		assertEquals(Boolean.FALSE, photo.isThumbLoaded());
		
	}

}
