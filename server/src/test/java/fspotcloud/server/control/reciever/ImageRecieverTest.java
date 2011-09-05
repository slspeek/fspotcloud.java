package fspotcloud.server.control.reciever;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import junit.framework.TestCase;

import org.mockito.ArgumentCaptor;

import com.google.common.collect.ImmutableList;

import fspotcloud.server.model.api.Photo;
import fspotcloud.server.model.api.Photos;
import fspotcloud.server.model.api.Tag;
import fspotcloud.server.model.api.Tags;
import fspotcloud.server.model.photo.PhotoDO;
import fspotcloud.server.model.tag.TagDO;
public class ImageRecieverTest extends TestCase {

	Photos photoManager;
	Photo photo;
	ImageReciever reciever;
	Tags tagManager;
	Tag tag;
	byte[] data;
		
	public void setUp() throws Exception {
		data = new byte[1];
		data[0] = 0;
		photoManager = mock(Photos.class);
		photo = new PhotoDO();
		photo.setId("photoID");
		photo.setTagList(ImmutableList.of("tagId"));
		tagManager = mock(Tags.class);
		tag = new TagDO();
		tag.setId("tagId");
		when(photoManager.getOrNew("photoId")).thenReturn(photo);
		when(tagManager.getById("tagId")).thenReturn(tag);
		
	}
	

	public void testImageReciever() {
		reciever = new ImageReciever(photoManager, tagManager);
		assertNotNull(reciever);
	}

	public void testRecieveImageData() {
		testImageReciever();
		reciever.recieveImageData("photoId", "fooData", data, Photo.IMAGE_TYPE_BIG);
		assertEquals("fooData", photo.getExifData());
		assertTrue(photo.isImageLoaded());
		verify(photoManager).save(photo);
	}
	
	public void testRecieveThumbData() {
		testImageReciever();
		reciever.recieveImageData("photoId", "fooData", data, Photo.IMAGE_TYPE_THUMB);
		assertEquals("fooData", photo.getExifData());
		assertTrue(photo.isThumbLoaded());
		verify(photoManager).save(photo);
	}

}
