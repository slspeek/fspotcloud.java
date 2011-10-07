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

import fspotcloud.server.model.api.Photo;
import fspotcloud.server.model.api.Photos;
import fspotcloud.server.model.photo.PhotoDO;
import fspotcloud.shared.peer.rpc.actions.PhotoData;
import fspotcloud.shared.peer.rpc.actions.PhotoDataResult;

public class PhotoDataCallbackTest extends TestCase {
	private static final String DESCRIPTION = "description";
	private static final String PHOTO_ID = "1";
	Photos photoManager;
	Photo photo1;
	PhotoDataResult result;
	PhotoData data;
	Date date = new Date(10);
	PhotoDataCallback callback;
	ArgumentCaptor<List<Photo>> argumentCaptor = (ArgumentCaptor<List<Photo>>) (Object) ArgumentCaptor
			.forClass(List.class);
	private ArrayList<PhotoData> dataList;

	@Override
	protected void setUp() throws Exception {
		photoManager = mock(Photos.class);
		photo1 = new PhotoDO();
		photo1.setId(PHOTO_ID);
		data = new PhotoData(PHOTO_ID, DESCRIPTION, date, null);
		dataList = new ArrayList<PhotoData>();
		dataList.add(data);
		result = new PhotoDataResult(dataList);
		when(photoManager.getOrNew(PHOTO_ID)).thenReturn(photo1);
		callback = new PhotoDataCallback(photoManager);
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
		assertNull(photo1.getTagList());
		verify(photoManager).saveAll(argumentCaptor.capture());
		assertEquals(photo1, argumentCaptor.getValue().get(0));
	}

}
