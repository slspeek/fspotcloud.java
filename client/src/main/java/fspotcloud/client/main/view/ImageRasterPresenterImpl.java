package fspotcloud.client.main.view;

import java.util.List;
import java.util.logging.Logger;

import fspotcloud.client.main.PagingNavigator;
import fspotcloud.client.main.view.api.ImageRasterView;
import fspotcloud.client.main.view.api.ImageView;
import fspotcloud.client.place.BasePlace;

public class ImageRasterPresenterImpl implements ImageView.ImagePresenter {
	private static final Logger log = Logger.getLogger(ImageRasterPresenterImpl.class
			.getName());

	final private String tagId;
	final private String photoId;
	final private int columnCount;
	final private int rowCount;
	final private ImageRasterView imageRasterView;
	final private PagingNavigator pager;
	
	List<ImageView> imageViewList;

	

	public ImageRasterPresenterImpl(BasePlace place, ImageRasterView imageRasterView, PagingNavigator pager) {
		tagId = place.getTagId();
		photoId = place.getPhotoId();
		columnCount = place.getColumnCount();
		rowCount = place.getRowCount();
		this.pager = pager;
		this.imageRasterView = imageRasterView;
	}

	public void init() {
		log.info("init");
		imageViewList = imageRasterView.buildRaster(rowCount, columnCount);
		setImages();
	}

	public void setImages() {
		
	}
}
