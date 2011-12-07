package fspotcloud.server.control.task.photodelete;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import net.customware.gwt.dispatch.shared.DispatchException;

import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;

import com.google.appengine.repackaged.com.google.common.collect.ImmutableList;

import fspotcloud.server.control.task.actions.intern.DeletePhotos;
import fspotcloud.server.model.api.Photo;
import fspotcloud.server.model.api.Photos;
import fspotcloud.server.model.api.Tag;
import fspotcloud.server.model.api.Tags;
import fspotcloud.server.model.photo.PhotoDO;
import fspotcloud.server.model.tag.TagDO;
import fspotcloud.shared.photo.PhotoInfo;
import fspotcloud.taskqueuedispatch.TaskQueueDispatch;
import fspotcloud.test.MockitoTestCase;
public class DeletePhotosHandlerTest extends MockitoTestCase {

	private static final String ID_B = "B";
	private static final String ID_A = "A";
	private static final String TAG_ID = "1";
	int MAX_DELETE_TICKS = 1;
	@Mock
	TaskQueueDispatch dispatchAsync;
	@Mock
	Photos photos;
	@Mock
	Tags tagManager;
	Tag tag, tag3;
	@Captor
	ArgumentCaptor<DeletePhotos> nextCallCaptor;
	
	PhotoInfo photoInfoA;
	PhotoInfo photoInfoB;
	List<PhotoInfo> infoList;
	Photo photoA;
	Photo photoB;
	List<String> tagIdListA = ImmutableList.of("1");
	List<String> tagIdListB = ImmutableList.of("1", "3");
	

	DeletePhotosHandler handler;
	protected void setUp() throws Exception {
		super.setUp();
		handler = new DeletePhotosHandler(MAX_DELETE_TICKS, dispatchAsync, photos, tagManager);
		tag3 = new TagDO();
		tag3.setId("3");
		tag3.setImportIssued(true);
		tag = new TagDO();
		tag.setId(TAG_ID);
		photoA = new PhotoDO();
		photoA.setTagList(tagIdListA);
		photoB = new PhotoDO();
		photoB.setTagList(tagIdListB);
		photoInfoA = new PhotoInfo(ID_A, "", new Date(10));
		photoInfoB = new PhotoInfo(ID_B, "", new Date(100000));
		infoList = new ArrayList<PhotoInfo>();
		infoList.add(photoInfoA);
		infoList.add(photoInfoB);
		SortedSet<PhotoInfo> cached = new TreeSet<PhotoInfo>();
		cached.add(photoInfoA);
		cached.add(photoInfoB);
		tag.setCachedPhotoList(cached);
		when(tagManager.getById(TAG_ID)).thenReturn(tag);
		when(tagManager.getById("3")).thenReturn(tag3);
		when(photos.getById(ID_A)).thenReturn(photoA);
		when(photos.getById(ID_B)).thenReturn(photoB);
		
	}

	/**
	 *  First gets deleted, the second is needed in another tag.
	 *  The max_delete = 1 so this takes 'recursion' over asyncDispatch.
	 * @throws DispatchException
	 */
	public void testExecute() throws DispatchException {
		handler.execute(new DeletePhotos(TAG_ID, infoList), null);
		assertEquals(1, tag.getCachedPhotoList().size());
		verify(photos).delete(ID_A);
		verify(photos).getById(ID_A);
		verifyNoMoreInteractions(photos);
		verify(dispatchAsync).execute(nextCallCaptor.capture());
		assertEquals(TAG_ID, nextCallCaptor.getValue().getTagId());
		handler.execute(new DeletePhotos(TAG_ID, infoList), null);
		verifyNoMoreInteractions(dispatchAsync);
		verify(photos).getById(ID_B);
		verifyNoMoreInteractions(photos);
		assertEquals(1, tag.getCachedPhotoList().size());
	}
}
