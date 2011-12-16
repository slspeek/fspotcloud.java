package fspotcloud.server.admin.actions;

import static org.mockito.Mockito.when;
import net.customware.gwt.dispatch.shared.DispatchException;

import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import fspotcloud.botdispatch.controller.dispatch.ControllerDispatchAsync;
import fspotcloud.server.control.task.actions.intern.PhotoUpdateAction;
import fspotcloud.server.model.api.Tags;
import fspotcloud.server.model.tag.TagDO;
import fspotcloud.shared.dashboard.actions.ImportTag;
import fspotcloud.taskqueuedispatch.SerializableAsyncCallback;

public class ImportTagHandlerTest {

	private static final int COUNT = 10;
	private static final int PREVIOUS_COUNT = 6;
	private static final String TAG_ID = "Foo";
	@Mock
	private Tags tagManager;
	@Mock
	private ControllerDispatchAsync dispatch;
	ImportTagHandler handler;
	ImportTag action = new ImportTag(TAG_ID, PREVIOUS_COUNT);
	TagDO tag;

	ArgumentCaptor<PhotoUpdateAction> actionCaptor;
	ArgumentCaptor<SerializableAsyncCallback> callbackCaptor;

	@BeforeMethod
	protected void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		handler = new ImportTagHandler(tagManager, dispatch);
		tag = new TagDO();
		tag.setId(TAG_ID);
		tag.setCount(COUNT);
		when(tagManager.getById(TAG_ID)).thenReturn(tag);
	}

	@Test
	public void testExecute() throws DispatchException {

	}

	@Test
	public void testExecuteAllreadyImported() throws DispatchException {

	}

}
