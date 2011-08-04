package fspotcloud.server.mapreduce;

import junit.framework.TestCase;

import org.jmock.Mockery;

import com.google.common.collect.ImmutableList;

import fspotcloud.server.control.SchedulerInterface;
import fspotcloud.server.model.api.Photo;
import fspotcloud.server.model.photo.PhotoDO;

public class ImageDataImporterNonRelevantTest extends TestCase {

	public ImmutableList<String> wantedTags = ImmutableList.of("1");
	public Photo photo = new PhotoDO();
	
	Mockery context;
	
	@Override
	protected void setUp() throws Exception {
		photo.setImageLoaded(false);
		photo.setThumbLoaded(false);
		photo.setFullsizeLoaded(false);
		photo.setTagList(ImmutableList.of("2"));
		context = new Mockery();		
		super.setUp();
	}



	public void testSchedule() {
		final SchedulerInterface scheduler = context.mock(SchedulerInterface.class);
		ImageDataImporter importer = new ImageDataImporter(photo, wantedTags, "1x1", "1x`1",scheduler);
		importer.schedule(Photo.IMAGE_TYPE_BIG);
		importer.schedule(Photo.IMAGE_TYPE_THUMB);
		context.assertIsSatisfied();
	}

}
