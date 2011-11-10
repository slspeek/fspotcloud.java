package fspotcloud.server.control.callback;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import junit.framework.TestCase;

import org.mockito.ArgumentCaptor;

import com.google.appengine.repackaged.com.google.common.collect.ImmutableList;

import fspotcloud.server.model.api.Photo;
import fspotcloud.server.model.api.Photos;
import fspotcloud.server.model.api.Tags;
import fspotcloud.server.model.photo.PhotoDO;
import fspotcloud.server.model.tag.TagDO;
import fspotcloud.shared.peer.rpc.actions.PhotoData;
import fspotcloud.shared.peer.rpc.actions.PhotoDataResult;
import fspotcloud.shared.photo.PhotoInfo;

public class PhotoDataCallbackTest extends TestCase {
	private static final byte[] IMAGE_DATA = new byte[] { 0, 1};
	private static final byte[] THUMB_DATA = new byte[] {0};
	private static final String DESCRIPTION = "description";
	private static final String PHOTO_ID = "1";
	private static final String TAG_ID = "foo";
	Photos photoManager;
	Tags tagManager;
	Photo photo1;
	PhotoDataResult result;
	PhotoData data;
	Date date = new Date(10);
	PhotoDataCallback callback;
	ArgumentCaptor<List<Photo>> argumentCaptor = (ArgumentCaptor<List<Photo>>) (Object) ArgumentCaptor
			.forClass(List.class);
	private ArrayList<PhotoData> dataList;
	private TagDO tag1;

	@Override
	protected void setUp() throws Exception {
		photoManager = mock(Photos.class);
		tagManager = mock(Tags.class);
		photo1 = new PhotoDO();
		tag1 = new TagDO();
		tag1.setId(TAG_ID);
		photo1.setId(PHOTO_ID);
		data = new PhotoData(PHOTO_ID, DESCRIPTION, date, IMAGE_DATA, THUMB_DATA, ImmutableList.of(TAG_ID));
		dataList = new ArrayList<PhotoData>();
		dataList.add(data);
		result = new PhotoDataResult(dataList);
		when(photoManager.getOrNew(PHOTO_ID)).thenReturn(photo1);
		when(tagManager.getById(TAG_ID)).thenReturn(tag1);
		callback = new PhotoDataCallback(photoManager, tagManager);
		super.setUp();
	}

	public void testSerialize() throws Exception {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ObjectOutputStream out = new ObjectOutputStream(bos);
		out.writeObject(callback);
		out.close();
	}

	public void testOnSuccess() {
		callback.onSuccess(result);
		assertEquals(date, photo1.getDate());
		assertEquals(DESCRIPTION, photo1.getDescription());
		verify(photoManager).saveAll(argumentCaptor.capture());
		assertEquals(photo1, argumentCaptor.getValue().get(0));
		assertEquals(IMAGE_DATA, photo1.getImage().getBytes());
		assertEquals(THUMB_DATA, photo1.getThumb().getBytes());
		assertTrue(photo1.isThumbLoaded());
		assertTrue(photo1.isImageLoaded());
		PhotoInfo info = tag1.getCachedPhotoList().first(); 
		assertEquals(PHOTO_ID, info.getId());
	}

}
