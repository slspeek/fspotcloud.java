package fspotcloud.server.mapreduce;

import junit.framework.TestCase;
import junit.framework.TestSuite;

import com.google.common.collect.ImmutableList;

import fspotcloud.botdispatch.controller.dispatch.ControllerDispatchAsync;
import fspotcloud.server.model.api.Photo;
import fspotcloud.server.model.photo.PhotoDO;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
public class ImageDataImporterRelevantTest extends TestCase {
	public static TestSuite suite() {
		return new TestSuite(ImageDataImporterRelevantTest.class);
	}
	public ImmutableList<String> wantedTags = ImmutableList.of("1");
	public Photo photo = new PhotoDO();
	ControllerDispatchAsync dispatch;

	@Override
	protected void setUp() throws Exception {
		photo.setId("1000");
		photo.setImageLoaded(false);
		photo.setThumbLoaded(false);
		photo.setFullsizeLoaded(false);
		photo.setTagList(ImmutableList.of("1"));
		dispatch = mock(ControllerDispatchAsync.class);
		super.setUp();
	}

	public void testScheduleBig() {
	
		ImageDataImporter importer = new ImageDataImporter(photo, wantedTags, "512x384", "1024x768",
				dispatch );
		importer.schedule(Photo.IMAGE_TYPE_BIG);
		
	}
	

	public void testScheduleThumb() {
		ImageDataImporter importer = new ImageDataImporter(photo, wantedTags, "512x384", "1024x768",
				dispatch);
		importer.schedule(Photo.IMAGE_TYPE_THUMB);
	}

	public void testShouldNotScheduleLoadedThumb() {
		photo.setThumbLoaded(true);
		ImageDataImporter importer = new ImageDataImporter(photo, wantedTags, "512x384",
				"1024x768",
				
				dispatch);
		importer.schedule(Photo.IMAGE_TYPE_THUMB);
	}
	
	public void testShouldNotScheduleLoadedImage() {
		photo.setImageLoaded(true);
		ImageDataImporter importer = new ImageDataImporter(photo, wantedTags,"512x384",
				"1024x768",
				
				dispatch);
		importer.schedule(Photo.IMAGE_TYPE_BIG);
	}
}
