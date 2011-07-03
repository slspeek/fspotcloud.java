package fspotcloud.client.main.view;

import java.util.List;
import java.util.logging.Logger;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;

import fspotcloud.client.main.Navigator;
import fspotcloud.client.main.view.api.ImageRasterView;
import fspotcloud.client.main.view.api.ImageView;
import fspotcloud.client.place.BasePlace;

public class ImageRasterPresenterImpl implements
		ImageRasterView.ImageRasterPresenter {
	private static final Logger log = Logger
			.getLogger(ImageRasterPresenterImpl.class.getName());

	private static final int MAGIC = 4;

	final private String tagId;
	final private String photoId;
	final private int columnCount;
	final private int rowCount;
	final private int pageSize;
	final private boolean thumb;
	final private ImageRasterView imageRasterView;
	final private Navigator pager;
	final private EventBus eventBus;

	List<ImageView> imageViewList;

	public ImageRasterPresenterImpl(BasePlace place,
			ImageRasterView imageRasterView, Navigator pager,
			EventBus eventBus) {
		tagId = place.getTagId();
		photoId = place.getPhotoId();
		columnCount = place.getColumnCount();
		rowCount = place.getRowCount();
		pageSize = columnCount * rowCount;
		thumb = pageSize > 1;
		this.pager = pager;
		this.imageRasterView = imageRasterView;
		this.eventBus = eventBus;
	}

	public void init() {
		log.info("init");
		imageViewList = imageRasterView.buildRaster(rowCount, columnCount);
		setImages();
	}

	public int getWidth() {
		return imageRasterView.asWidget().getElement().getClientWidth();
	}

	public int getHeight() {
		return imageRasterView.asWidget().getElement().getClientHeight();
	}
	
	private int getImageWidth() {
		return (int)((float)getWidth()/(float)columnCount) - MAGIC;
	}

	private int getImageHeight() {
		return (int)((float)getHeight()/(float)rowCount) - MAGIC;
	}

	public void setImages() {
		pager.getPageAsync(tagId, photoId, pageSize,
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
					getImageWidth(), getImageHeight(), result.get(i), imageViewList.get(i),
					thumb, eventBus);
			presenter.init();
		}
	}
}
