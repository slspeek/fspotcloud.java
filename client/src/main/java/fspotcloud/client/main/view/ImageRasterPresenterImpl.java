package fspotcloud.client.main.view;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

import fspotcloud.client.main.view.api.ImagePresenterFactory;
import fspotcloud.client.main.view.api.ImageRasterView;
import fspotcloud.client.main.view.api.ImageView;
import fspotcloud.client.place.BasePlace;
import fspotcloud.client.place.api.Navigator;

public class ImageRasterPresenterImpl extends AbstractActivity implements
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
	protected final ImageRasterView imageRasterView;
	final private Navigator pager;
	final private ImagePresenterFactory imagePresenterFactory;
	List<ImageView> imageViewList;
	List<ImageView.ImagePresenter> imagePresenterList = new ArrayList<ImageView.ImagePresenter>();

	@Inject
	public ImageRasterPresenterImpl(@Assisted BasePlace place,
			@Assisted ImageRasterView imageRasterView, Navigator pager,
			ImagePresenterFactory imagePresenterFactory) {
		tagId = place.getTagId();
		photoId = place.getPhotoId();
		columnCount = place.getColumnCount();
		rowCount = place.getRowCount();
		pageSize = columnCount * rowCount;
		thumb = pageSize > 1;
		this.pager = pager;
		this.imageRasterView = imageRasterView;
		this.imagePresenterFactory = imagePresenterFactory;
	}

	public void init() {
		log.info("init");
		imageRasterView.setPresenter(this);
		imageViewList = imageRasterView.buildRaster(rowCount, columnCount);
		setImages();
	}

	public int getWidth() {
		return imageRasterView.asWidget().getOffsetWidth();
	}

	public int getHeight() {
		return imageRasterView.asWidget().getOffsetHeight();
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
						

					}
				});

	}

	private void setImages(List<BasePlace> result) {
		imagePresenterList.clear();
		for (int i = 0; i < result.size(); i++) {
			ImageView.ImagePresenter presenter = imagePresenterFactory.get(
					getImageWidth(), getImageHeight(), result.get(i), imageViewList.get(i),
					thumb);
			imagePresenterList.add(presenter);
			presenter.init();
		}
	}

	@Override
	public void onResize() {
		for (ImageView.ImagePresenter presenter: imagePresenterList) {
			presenter.setMaxWidth(getImageWidth());
			presenter.setMaxHeight(getImageHeight());
		}
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		panel.setWidget(imageRasterView);
		
	}

	
}
