package fspotcloud.server.inject;

import com.google.common.collect.ImmutableList;
import com.google.inject.assistedinject.Assisted;

import fspotcloud.server.mapreduce.ImageDataImporter;
import fspotcloud.server.model.api.Photo;

public interface ImageDataImporterFactory {
	ImageDataImporter create(Photo photo, ImmutableList<String> tagsForImport,
			@Assisted("thumb") String thumbDimension, @Assisted("image") String imageDimension);
}
