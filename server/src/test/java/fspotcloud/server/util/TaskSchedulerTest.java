package fspotcloud.server.util;

import static com.google.appengine.api.taskqueue.TaskOptions.Builder.withUrl;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.jmock.Expectations;
import org.jmock.Mockery;

import com.google.appengine.api.taskqueue.Queue;
import com.google.appengine.api.taskqueue.QueueFactory;
import com.google.appengine.api.taskqueue.TaskOptions;
import com.google.common.collect.ImmutableMap;


public class TaskSchedulerTest extends TestCase {

	public static TestSuite suite() {
		return new TestSuite(TaskSchedulerTest.class);
	}
	
	Mockery context = new Mockery();

	public final void testTaskScheduler() {
		Queue defaultQ = QueueFactory.getDefaultQueue();
		TaskScheduler scheduler = new TaskScheduler(defaultQ);
		assertNotNull(scheduler);
	}

	public final void testScheduleUrl() {
		final Queue queue = context.mock(Queue.class);
		final String url = "foo.com";
		final TaskScheduler scheduler = new TaskScheduler(queue);
		
		final TaskOptions options = withUrl(url).param("ape", "chimp");
		
		context.checking(new Expectations() {
			{
				oneOf(queue).add(options);
			}
		});
		scheduler.schedule(url, ImmutableMap.of("ape", "chimp"));
		context.assertIsSatisfied();
	}

}
