package fspotcloud.server.util;

import static com.google.appengine.api.labs.taskqueue.TaskOptions.Builder.url;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import com.google.appengine.api.labs.taskqueue.TaskOptions;


public class TaskOptionsTest extends TestCase {
	
	public static TestSuite suite() {
		return new TestSuite(TaskOptionsTest.class);
	}
	
	public void testEquals() {
		TaskOptions to1 = url("foo");
		TaskOptions to2 = url("foo");
		//XXX This is strange
		assertFalse(to1.equals(to2));
	}

}
