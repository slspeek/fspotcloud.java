package fspotcloud.server.control.reciever;

import java.util.Date;
import java.util.List;

import org.mockito.ArgumentCaptor;

import com.google.common.collect.ImmutableList;

import fspotcloud.server.model.api.Photo;
import fspotcloud.server.model.api.Photos;import fspotcloud.server.model.photo.PhotoDO;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import junit.framework.TestCase;

public class PhotoRecieverTest extends TestCase {
	Photos photoManager;
	Photo photo1;
	Object[] data;
	Object[] row;
	Date date = new Date(10);
	PhotoReciever reciever;
	ArgumentCaptor<List<Photo>> argumentCaptor = (ArgumentCaptor<List<Photo>>) (Object) ArgumentCaptor.forClass(List.class);
	
	@Override
	protected void setUp() throws Exception {
		photoManager = mock(Photos.class);
		photo1 = new PhotoDO();
		photo1.setId("1");
		row = new Object[] { "1", "description", date, new Object[]{} };
		data = new Object[] { row };
		when(photoManager.getOrNew("1")).thenReturn(photo1);
		super.setUp();
	}

	public void testPhotoReciever() {
		reciever = new PhotoReciever(photoManager);
		assertNotNull(reciever);
	}

	public void testRecievePhotoData() {
		testPhotoReciever();
		reciever.recievePhotoData(data);
		assertEquals(date, photo1.getDate());
		assertEquals("description", photo1.getDescription());
		assertEquals(0, photo1.getTagList().size());
		verify(photoManager).saveAll(argumentCaptor.capture());
		assertEquals(photo1, argumentCaptor.getValue().get(0));
	}

}
