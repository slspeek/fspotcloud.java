package fspotcloud.server.admin.actions;

import static org.mockito.Mockito.*;
import net.customware.gwt.dispatch.shared.DispatchException;
import junit.framework.TestCase;
import fspotcloud.server.control.task.photoimport.PhotoImportScheduler;
import fspotcloud.server.model.api.Tags;
import fspotcloud.server.model.tag.TagDO;
import fspotcloud.shared.dashboard.actions.ImportTag;
public class ImportTagHandlerTest extends TestCase {

	
	private static final int COUNT = 10;
	private static final int PREVIOUS_COUNT = 6;
	private static final String TAG_ID = "Foo";
	private Tags tagManager;
	private PhotoImportScheduler scheduler;
	ImportTagHandler handler;
	ImportTag action = new ImportTag(TAG_ID, PREVIOUS_COUNT);
	TagDO tag;

	protected void setUp() throws Exception {
		super.setUp();
		tagManager = mock(Tags.class);
		scheduler = mock(PhotoImportScheduler.class);
		handler = new ImportTagHandler(tagManager, scheduler);
		tag = new TagDO();
		tag.setId(TAG_ID);
		tag.setCount(COUNT);
		when(tagManager.getById(TAG_ID)).thenReturn(tag);
	}

	public void testExecute() throws DispatchException {
		handler.execute(action, null);
		verify(scheduler).schedulePhotoImport(TAG_ID, "", PREVIOUS_COUNT, COUNT-PREVIOUS_COUNT);
		verify(tagManager).save(tag);
	}
	
	public void testExecuteAllreadyImported() throws DispatchException {
		tag.setImportIssued(true);
		handler.execute(action, null);
		verify(scheduler).schedulePhotoImport(TAG_ID, "", PREVIOUS_COUNT, COUNT-PREVIOUS_COUNT);
		//verifyNoMoreInteractions(tagManager);
	}

}
