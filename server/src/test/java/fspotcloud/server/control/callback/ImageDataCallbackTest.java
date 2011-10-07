package fspotcloud.server.control.callback;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import junit.framework.TestCase;

import com.google.common.collect.ImmutableList;

import fspotcloud.server.model.api.Photo;
import fspotcloud.server.model.api.Photos;
import fspotcloud.server.model.api.Tag;
import fspotcloud.server.model.api.Tags;
import fspotcloud.server.model.photo.PhotoDO;
import fspotcloud.server.model.tag.TagDO;
import fspotcloud.shared.peer.rpc.actions.ImageDataResult;

public class ImageDataCallbackTest extends TestCase {

	private static final String TAG_ID = "tagId";
	private static final String PHOTO_ID = "1";
	private static final String EXIF_STRING = "Exif string";
	Photos photoManager;
	Photo photo;
	ImageDataCallback reciever;
	Tags tagManager;
	Tag tag;
	byte[] data;
	ImageDataResult result1;
	ImageDataResult result2;
	private ImageDataCallback reciever2;
	private ImageDataCallback reciever1;
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		data = new byte[1];
		data[0] = 0;
		photoManager = mock(Photos.class);
		photo = new PhotoDO();
		photo.setId(PHOTO_ID);
		photo.setTagList(ImmutableList.of(TAG_ID));
		tagManager = mock(Tags.class);
		tag = new TagDO();
		tag.setId(TAG_ID);
		when(photoManager.getOrNew(PHOTO_ID)).thenReturn(photo);
		when(tagManager.getById(TAG_ID)).thenReturn(tag);
		result1 = new ImageDataResult(data, EXIF_STRING);
		result2 = new ImageDataResult(data, EXIF_STRING);
		reciever1 = new ImageDataCallback(photoManager, tagManager, Photo.IMAGE_TYPE_BIG,  PHOTO_ID);
		reciever2 = new ImageDataCallback(photoManager, tagManager, Photo.IMAGE_TYPE_THUMB,  PHOTO_ID);
	}

	public void testOnSuccess1() {
		reciever1.onSuccess(result1);
		assertEquals(EXIF_STRING, photo.getExifData());
		assertTrue(photo.isImageLoaded());
		verify(photoManager).save(photo);
	}

	public void testOnSuccess2() {
		reciever2.onSuccess(result2);
		assertEquals(EXIF_STRING, photo.getExifData());
		assertTrue(photo.isThumbLoaded());
		verify(photoManager).save(photo);
	}
}
