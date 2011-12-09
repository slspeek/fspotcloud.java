package fspotcloud.taskqueuedispatch.inject;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.google.inject.Guice;
import com.google.inject.Injector;

import fspotcloud.taskqueuedispatch.TaskQueueDispatch;


public class TaskQueueDispatchDirectModuleTest extends TestCase {

	private final LocalServiceTestHelper helper =
        new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig());

	TaskQueueDispatchModule module;
	@Before
    public void setUp() {
        helper.setUp();
        module = new TaskQueueDispatchModule();
	}

	@Test
	public void testCreateTaskQueueDispatch() {
		Injector i = Guice.createInjector(module);
		TaskQueueDispatch queueDispatch = i.getInstance(TaskQueueDispatch.class);
		assertNotNull(queueDispatch);
	}
	
	@After
    public void tearDown() {
        helper.tearDown();
    }
	
}
