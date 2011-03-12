package fspotcloud.server.util;

import java.util.Collections;

import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.jmock.Expectations;
import org.jmock.Mockery;

import com.google.appengine.api.labs.taskqueue.Queue;
import com.google.appengine.api.labs.taskqueue.QueueFactory;
import com.google.appengine.api.labs.taskqueue.TaskOptions;

public class TaskSchedulerTest extends TestCase {

	public static TestSuite suite() {
		return new TestSuite(TaskSchedulerTest.class);
	}
	
	Mockery context = new Mockery();

	public final void testTaskScheduler() {
		Queue defaultQ = QueueFactory.getDefaultQueue();
		TaskScheduler scheduler = new TaskScheduler(defaultQ);
	}

	public final void testScheduleUrl() {
		final Queue queue = context.mock(Queue.class);
		final String url = "foo.com";
		final TaskScheduler scheduler = new TaskScheduler(queue);

		context.checking(new Expectations() {
			{
				oneOf(queue).add(with(aNonNull(TaskOptions.class)));
				// oneOf(queue).add(options);
			}
		});
		scheduler.schedule(url, Collections.EMPTY_MAP);
		context.assertIsSatisfied();
	}

}
