package com.googlecode.fspotcloud.client.main.view;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

import com.googlecode.fspotcloud.client.main.view.api.ImagePresenterFactory;
import com.googlecode.fspotcloud.client.main.view.api.ImageRasterView;
import com.googlecode.fspotcloud.client.main.view.api.ImageView;
import com.googlecode.fspotcloud.client.place.BasePlace;
import com.googlecode.fspotcloud.client.place.api.Navigator;
import com.googlecode.fspotcloud.shared.photo.PhotoInfo;

public class ImageRasterPresenterImpl extends AbstractActivity implements
		ImageRasterView.ImageRasterPresenter {
	private static final Logger log = Logger
			.getLogger(ImageRasterPresenterImpl.class.getName());

	final private String tagId;
	final private String photoId;
	final private int columnCount;
	final private int rowCount;
	final private int pageSize;
	final private boolean thumb;
	protected final ImageRasterView imageRasterView;
	final private Navigator navigator;
	final private ImagePresenterFactory imagePresenterFactory;
	List<ImageView> imageViewList;
	List<ImageView.ImagePresenter> imagePresenterList = new ArrayList<ImageView.ImagePresenter>();

	@Inject
	public ImageRasterPresenterImpl(@Assisted BasePlace place,
			@Assisted ImageRasterView imageRasterView, Navigator navigator,
			ImagePresenterFactory imagePresenterFactory) {
		tagId = place.getTagId();
		photoId = place.getPhotoId();
		columnCount = place.getColumnCount();
		rowCount = place.getRowCount();
		pageSize = columnCount * rowCount;
		thumb = pageSize > 1;
		this.navigator = navigator;
		this.imageRasterView = imageRasterView;
		this.imagePresenterFactory = imagePresenterFactory;
	}

	public void init() {
		log.info("init");
		imageRasterView.setPresenter(this);
		setImages();
	}

	
	public void setImages() {
		navigator.getPageAsync(tagId, photoId, pageSize,
				new AsyncCallback<List<PhotoInfo>>() {

					@Override
					public void onSuccess(List<PhotoInfo> result) {
						imageViewList = imageRasterView.buildRaster(rowCount,
								columnCount);
						setImages(result);
						navigator.getPageRelativePositionAsync( tagId, photoId, pageSize,
								new AsyncCallback<Integer[]>() {

									@Override
									public void onFailure(Throwable caught) {
										imageRasterView.setPagingText(caught.getMessage());

									}

									@Override
									public void onSuccess(Integer[] result) {
										String label = (result[0] + 1) + " of " + result[1];
										imageRasterView.setPagingText(label);
									}
								});
					}

					@Override
					public void onFailure(Throwable caught) {

					}
				});

	}

	private void setImages(List<PhotoInfo> result) {
		imagePresenterList.clear();
		int i = 0;
		for (; i < result.size(); i++) {
			ImageView view = imageViewList.get(i);
			view.asWidget().setVisible(true);
			final ImageView.ImagePresenter presenter = imagePresenterFactory.get(tagId, result.get(i),
					view, thumb);
			if (result.get(i).getId().equals(photoId) && pageSize > 1) {
				presenter.setSelected(true);
			} else {
				presenter.setSelected(false);
			}
			imagePresenterList.add(presenter);
			Scheduler.get().scheduleDeferred(new ScheduledCommand() {
				
				@Override
				public void execute() {
					// TODO Auto-generated method stub
					presenter.init();	
				}
			});
		}
		for(; i < pageSize; i++) {
			ImageView view = imageViewList.get(i); 
			view.setImageUrl("");
			view.setSelected(false);
			view.setDescription("");
			view.asWidget().setVisible(false);
		}
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		panel.setWidget(imageRasterView);
	}

	@Override
	public void onMouseWheelNorth() {
		navigator.goAsync(Navigator.Direction.BACKWARD, Navigator.Unit.PAGE);
	}

	@Override
	public void onMouseWheelSouth() {
		navigator.goAsync(Navigator.Direction.FORWARD, Navigator.Unit.PAGE);
	}

}
