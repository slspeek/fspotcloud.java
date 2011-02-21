package fspotcloud.client.view;

import java.util.List;
import java.util.logging.Logger;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.activity.shared.ActivityManager;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;
import com.google.gwt.view.client.SelectionChangeEvent.Handler;
import com.google.inject.Inject;

import fspotcloud.client.data.DataManager;
import fspotcloud.shared.tag.TagNode;

public class TagActivity extends AbstractActivity implements Handler,
		TagView.TagPresenter {
	private static final Logger log = Logger.getLogger(TagActivity.class
			.getName());

	final DataManager dataManager;
	final TagView tagView;
	final private PlaceGoTo placeGoTo;
	final private ImageViewActivityMapper imageActivityMapper;
	final private EventBus eventBus;
	
	String tagId;

	private SingleSelectionModel<TagNode> selectionModel;

	@Inject
	public TagActivity(TagView tagView, DataManager dataManager,
			PlaceGoTo placeGoTo, SingleSelectionModel<TagNode> selectionModel,
			ImageViewActivityMapper imageActivityMapper, EventBus eventBus) {
		this.tagView = tagView;
		this.eventBus = eventBus;
		this.dataManager = dataManager;
		this.placeGoTo = placeGoTo;
		this.imageActivityMapper = imageActivityMapper;
		this.selectionModel = selectionModel;
		selectionModel.addSelectionChangeHandler(this);
		log.info("TagActivity Created");
	}

	private void initActivityManager() {
		ActivityManager activityManager = new ActivityManager(
				imageActivityMapper, eventBus);
		activityManager.setDisplay(tagView.getImageViewContainer());
	}
	
	public void init() {
		initActivityManager();
	}

	@Override
	public void onStop() {
		// embeddedImagePresenter.onStop();
		super.onStop();
	}

	public void setPlace(BasePlace place) {
		tagId = place.getTagId();
		TagNode node = new TagNode();
		node.setId(tagId);
		selectionModel.setSelected(node, true);
	}

	@Override
	public void start(AcceptsOneWidget containerWidget, EventBus eventBus) {
		log.info("Start tag activity for tagId: " + tagId);
		containerWidget.setWidget(tagView);
	}

	protected void goToPhoto(String otherTagId, String photoId) {
		placeGoTo.goTo(new TagViewingPlace(otherTagId, photoId));
	}

	private void requestTagTreeData() {
		dataManager.getTagTree(new AsyncCallback<List<TagNode>>() {
			@Override
			public void onFailure(Throwable caught) {

			}

			@Override
			public void onSuccess(List<TagNode> result) {
				TagTreeModel treeModel = new TagTreeModel(result,
						selectionModel);
				tagView.setTreeModel(treeModel);
			}
		});
	}

	public void reloadTree() {
		requestTagTreeData();
	}

	@Override
	public void onSelectionChange(SelectionChangeEvent event) {
		// log.info("OnSelectionChange: " + event);
		TagNode node = selectionModel.getSelectedObject();
		if (node != null) {
			if (!node.getCachedPhotoList().isEmpty()) {
				String firstPhotoId = node.getCachedPhotoList().get(0).getId();
				String tagId = node.getId();
				goToPhoto(tagId, firstPhotoId);
			}
		}
	}

}
