package fspotcloud.server.control.task;

import junit.framework.TestCase;

import org.jmock.Expectations;
import org.jmock.Mockery;

import com.google.appengine.repackaged.com.google.common.collect.ImmutableList;

import fspotcloud.server.control.SchedulerInterface;

public class PhotoDataSchedulerImplTest extends TestCase {

	Mockery context;
	SchedulerInterface scheduler;
	DataScheduler recursiveCall;
	DataScheduler photoDataImporter;

	@Override
	protected void setUp() throws Exception {
		context = new Mockery();
		recursiveCall = context.mock(DataScheduler.class);
		scheduler = context.mock(SchedulerInterface.class);
		super.setUp();
	}

	public void testPhotoDataSchedulerImpl() {
		photoDataImporter = new DataSchedulerImpl(scheduler, 2, "Photo",
				recursiveCall);
		assertNotNull(photoDataImporter);
	}

	public void testSchedulePhotoDataImport() {
		testPhotoDataSchedulerImpl();
		context.checking(new Expectations() {
			{
				oneOf(recursiveCall).scheduleDataImport(14, 1);
				oneOf(scheduler).schedule("sendPhotoData",
						ImmutableList.of("10", "2"));
				oneOf(scheduler).schedule("sendPhotoData",
						ImmutableList.of("12", "2"));
			}
		});
		photoDataImporter.scheduleDataImport(10, 5);
	}

	public void testSchedulePhotoDataImportSingleRun() {
		testPhotoDataSchedulerImpl();
		context.checking(new Expectations() {
			{
				oneOf(scheduler).schedule("sendPhotoData",
						ImmutableList.of("10", "2"));
				oneOf(scheduler).schedule("sendPhotoData",
						ImmutableList.of("12", "2"));
			}
		});
		photoDataImporter.scheduleDataImport(10, 4);
	}

}
