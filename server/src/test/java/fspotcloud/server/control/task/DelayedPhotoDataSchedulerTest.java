package fspotcloud.server.control.task;

import static com.google.appengine.api.taskqueue.TaskOptions.Builder.withUrl;
import junit.framework.TestCase;

import org.jmock.Expectations;
import org.jmock.Mockery;

import com.google.appengine.api.taskqueue.Queue;
import com.google.appengine.api.taskqueue.TaskOptions;

public class DelayedPhotoDataSchedulerTest extends TestCase {

	Mockery context;
	Queue queue;
	DelayedPhotoDataScheduler scheduler;
	@Override
	protected void setUp() throws Exception {
		context = new Mockery();
		queue = context.mock(Queue.class);
		super.setUp();
	}
	
	public void testConstructor() {
		scheduler = new DelayedPhotoDataScheduler(queue);
		assertNotNull(scheduler);
	}
	
	public void testSchedule() {
		testConstructor();
		final TaskOptions taskOptions = withUrl("/control/task/photoData").param("offset","0").param("limit","10");
		context.checking(new Expectations() {{
			oneOf(queue).add(taskOptions);
		}});
		scheduler.schedulePhotoDataImport(0, 10);
	}
}
