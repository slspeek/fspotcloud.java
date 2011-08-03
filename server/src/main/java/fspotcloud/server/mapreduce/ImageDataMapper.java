package fspotcloud.server.mapreduce;

import java.io.IOException;
import java.util.logging.Logger;

import org.apache.hadoop.io.NullWritable;

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.tools.mapreduce.AppEngineMapper;
import com.google.common.collect.ImmutableList;
import com.google.inject.Guice;
import com.google.inject.Injector;

import fspotcloud.server.control.task.TaskModule;
import fspotcloud.server.inject.FSpotCloudModule;
import fspotcloud.server.inject.ImageDataImporterFactory;
import fspotcloud.server.model.api.PeerDatabase;
import fspotcloud.server.model.api.PeerDatabases;
import fspotcloud.server.model.api.Photo;
import fspotcloud.server.model.photo.PhotoDOBuilder;

public class ImageDataMapper extends
		AppEngineMapper<Key, Entity, NullWritable, NullWritable> {
	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(ImageDataMapper.class
			.getName());

	private final static Injector injector = Guice
			.createInjector(new FSpotCloudModule(), new TaskModule());

	private ImageDataImporterFactory factory = injector
			.getInstance(ImageDataImporterFactory.class);

	private PhotoDOBuilder builder = new PhotoDOBuilder();
	private ImmutableList<String> wantedTags;
	private String thumbDimension;
	private String imageDimension;

	public ImageDataMapper() {
	}

	@Override
	public void taskSetup(Context arg0) throws IOException,
			InterruptedException {
		PeerDatabases defaultPeer = injector.getInstance(PeerDatabases.class);
		PeerDatabase pd = defaultPeer.get();
		wantedTags = ImmutableList.copyOf(pd.getCachedImportedTags());
		thumbDimension = pd.getThumbDimension();
		imageDimension = pd.getImageDimension();
		super.taskSetup(arg0);
	}

	@Override
	public void map(Key key, Entity value, Context context) {
		Photo photo = builder.create(value);
		photo.setId(key.getName());
		ImageDataImporter importer = factory.create(photo, wantedTags,
				thumbDimension, imageDimension);
		importer.schedule(Photo.IMAGE_TYPE_BIG);
		importer.schedule(Photo.IMAGE_TYPE_THUMB);
	}
}