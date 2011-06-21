package fspotcloud.client.main.view;

import java.util.List;
import java.util.logging.Logger;

import com.google.gwt.user.client.rpc.AsyncCallback;

import fspotcloud.client.main.PagingNavigator;
import fspotcloud.client.main.view.api.ImageRasterView;
import fspotcloud.client.main.view.api.ImageView;
import fspotcloud.client.place.BasePlace;

public class ImageRasterPresenterImpl implements ImageRasterView.ImageRasterPresenter {
	private static final Logger log = Logger
			.getLogger(ImageRasterPresenterImpl.class.getName());

	final private String tagId;
	final private String photoId;
	final private int columnCount;
	final private int rowCount;
	final private int pageSize;
	final private boolean thumb;
	final private ImageRasterView imageRasterView;
	final private PagingNavigator pager;

	List<ImageView> imageViewList;

	public ImageRasterPresenterImpl(BasePlace place,
			ImageRasterView imageRasterView, PagingNavigator pager) {
		tagId = place.getTagId();
		photoId = place.getPhotoId();
		columnCount = place.getColumnCount();
		rowCount = place.getRowCount();
		pageSize = columnCount * rowCount;
		thumb = pageSize > 1;
		this.pager = pager;
		this.imageRasterView = imageRasterView;
	}

	public void init() {
		log.info("init");
		imageViewList = imageRasterView.buildRaster(rowCount, columnCount);
		setImages();
	}

	public void setImages() {
		pager.getPage(tagId, photoId, pageSize,
				new AsyncCallback<List<BasePlace>>() {

					@Override
					public void onSuccess(List<BasePlace> result) {
						setImages(result);

					}

					@Override
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub

					}
				});

	}

	private void setImages(List<BasePlace> result) {
		for (int i = 0; i < result.size(); i++) {
			ImageView.ImagePresenter presenter = new ImagePresenterImpl(
					result.get(i), imageViewList.get(i), thumb);
			presenter.init();
		}
	}
}
