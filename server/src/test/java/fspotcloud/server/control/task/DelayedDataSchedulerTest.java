package fspotcloud.server.control.task;

import static com.google.appengine.api.taskqueue.TaskOptions.Builder.withUrl;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.jmock.Expectations;
import org.jmock.Mockery;

import com.google.appengine.api.taskqueue.Queue;
import com.google.appengine.api.taskqueue.TaskOptions;

import fspotcloud.server.control.task.tagimport.DelayedDataScheduler;

public class DelayedDataSchedulerTest extends TestCase {

	public static TestSuite suite() {
		return new TestSuite(DelayedDataSchedulerTest.class);
	}
	Mockery context;
	Queue queue;
	DelayedDataScheduler scheduler;
	@Override
	protected void setUp() throws Exception {
		context = new Mockery();
		queue = context.mock(Queue.class);
		super.setUp();
	}
	
	public void testConstructor() {
		scheduler = new DelayedDataScheduler(queue, "Photo");
		assertNotNull(scheduler);
	}
	
	public void testSchedule() {
		testConstructor();
		final TaskOptions taskOptions = withUrl("/control/task/data").param("offset","0").param("limit","10").param("kind", "Photo");
		context.checking(new Expectations() {{
			oneOf(queue).add(taskOptions);
		}});
		scheduler.scheduleDataImport(0, 10);
	}
}
