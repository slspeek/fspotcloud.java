package fspotcloud.server.control.task;

import junit.framework.TestCase;

import org.jmock.Expectations;
import org.jmock.Mockery;

import com.google.appengine.repackaged.com.google.common.collect.ImmutableList;

import fspotcloud.server.control.SchedulerInterface;

public class PhotoDataSchedulerImplTest extends TestCase {

	Mockery context;
	SchedulerInterface scheduler;
	PhotoDataScheduler recursiveCall;
	PhotoDataScheduler photoDataImporter;
	
	@Override
	protected void setUp() throws Exception {
		context = new Mockery();
		recursiveCall = context.mock(PhotoDataScheduler.class);
		scheduler = context.mock(SchedulerInterface.class);
		super.setUp();
	}
	
	public void testPhotoDataSchedulerImpl() {
		photoDataImporter = new PhotoDataSchedulerImpl(recursiveCall, scheduler, 2);
		assertNotNull(photoDataImporter);
	}

	public void testSchedulePhotoDataImport() {
		testPhotoDataSchedulerImpl();
		context.checking(new Expectations() {{
			oneOf(recursiveCall).schedulePhotoDataImport(14, 1);
			oneOf(scheduler).schedule("sendPhotoData", ImmutableList.of("10", "2"));
			oneOf(scheduler).schedule("sendPhotoData", ImmutableList.of("12", "2"));
		}});
		photoDataImporter.schedulePhotoDataImport(10, 5);
	}

}
