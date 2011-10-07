package fspotcloud.server.mapreduce;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.jmock.Mockery;

import com.google.common.collect.ImmutableList;

import fspotcloud.botdispatch.controller.dispatch.ControllerDispatchAsync;
import fspotcloud.server.model.api.Photo;
import fspotcloud.server.model.photo.PhotoDO;

public class ImageDataImporterNonRelevantTest extends TestCase {
	public static TestSuite suite() {
		return new TestSuite(ImageDataImporterNonRelevantTest.class);
	}
	public ImmutableList<String> wantedTags = ImmutableList.of("1");
	public Photo photo = new PhotoDO();
	ControllerDispatchAsync dispatch;
	
	Mockery context;
	
	@Override
	protected void setUp() throws Exception {
		photo.setImageLoaded(false);
		photo.setThumbLoaded(false);
		photo.setFullsizeLoaded(false);
		photo.setTagList(ImmutableList.of("2"));
		dispatch = mock(ControllerDispatchAsync.class);
		super.setUp();
	}



	public void testSchedule() {
		ImageDataImporter importer = new ImageDataImporter(photo, wantedTags, "1x1", "1x1",dispatch);
		importer.schedule(Photo.IMAGE_TYPE_BIG);
		importer.schedule(Photo.IMAGE_TYPE_THUMB);
		verifyNoMoreInteractions(dispatch);
	}

}
