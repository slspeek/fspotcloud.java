package fspotcloud.server.mapreduce;

import junit.framework.TestCase;

import org.jmock.Expectations;
import org.jmock.Mockery;

import com.google.common.collect.ImmutableList;

import fspotcloud.server.control.SchedulerInterface;
import fspotcloud.server.model.api.Photo;
import fspotcloud.server.model.photo.PhotoDO;

public class ImageDataImporterRelevantTest extends TestCase {

	public ImmutableList<String> wantedTags = ImmutableList.of("1");
	public Photo photo = new PhotoDO();

	Mockery context;

	@Override
	protected void setUp() throws Exception {
		photo.setId("1000");
		photo.setImageLoaded(false);
		photo.setThumbLoaded(false);
		photo.setFullsizeLoaded(false);
		photo.setTagList(ImmutableList.of("1"));
		context = new Mockery();
		super.setUp();
	}

	public void testScheduleBig() {
		final SchedulerInterface scheduler = context
				.mock(SchedulerInterface.class);
		ImageDataImporter importer = new ImageDataImporter(photo, wantedTags, "512x384", "1024x768",
				scheduler );
		context.checking(new Expectations() {
			{
				oneOf(scheduler).schedule(
						"sendImageData",
						ImmutableList.of("1000",
								"1024","768",
								"1"));
			}
		});

		importer.schedule(Photo.IMAGE_TYPE_BIG);
		context.assertIsSatisfied();
	}
	

	public void testScheduleThumb() {
		final SchedulerInterface scheduler = context
				.mock(SchedulerInterface.class);
		ImageDataImporter importer = new ImageDataImporter(photo, wantedTags, "512x384", "1024x768",
				scheduler);
		context.checking(new Expectations() {
			{
				oneOf(scheduler).schedule(
						"sendImageData",
						ImmutableList.of("1000",
								"512","384",
								"0"));
			}
		});

		importer.schedule(Photo.IMAGE_TYPE_THUMB);
		context.assertIsSatisfied();
	}

	public void testShouldNotScheduleLoadedThumb() {
		final SchedulerInterface scheduler = context
				.mock(SchedulerInterface.class);
		photo.setThumbLoaded(true);
		ImageDataImporter importer = new ImageDataImporter(photo, wantedTags, "512x384",
				"1024x768",
				
				scheduler);
		context.checking(new Expectations() {
			{
				
			}
		});

		importer.schedule(Photo.IMAGE_TYPE_THUMB);
		context.assertIsSatisfied();
	}
	
	public void testShouldNotScheduleLoadedImage() {
		final SchedulerInterface scheduler = context
				.mock(SchedulerInterface.class);
		photo.setImageLoaded(true);
		ImageDataImporter importer = new ImageDataImporter(photo, wantedTags,"512x384",
				"1024x768",
				
				scheduler);
		context.checking(new Expectations() {
			{
				
			}
		});

		importer.schedule(Photo.IMAGE_TYPE_BIG);
		context.assertIsSatisfied();
	}
}
