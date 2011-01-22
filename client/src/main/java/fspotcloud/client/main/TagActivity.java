package fspotcloud.client.main;

import java.util.List;
import java.util.logging.Logger;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.activity.shared.ActivityManager;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.TreeItem;
import com.google.inject.Inject;

import fspotcloud.client.main.ImageView.ImagePresenter;
import fspotcloud.shared.tag.TagNode;

public class TagActivity extends AbstractActivity implements
		TagView.TagPresenter {
	private static final Logger log = Logger.getLogger(TagActivity.class
			.getName());

	final DataManager dataManager;
	final TagView tagView;
	final private PlaceController placeController;
	final private TagActivityMapper tagActivityMapper;
	final private EventBus eventBus;

	String tagId;
	String photoId;

	@Inject
	public TagActivity(TagView tagView, DataManager dataManager,
			PlaceController placeController,
			TagActivityMapper tagActivityMapper, EventBus eventBus) {
		this.tagView = tagView;
		this.dataManager = dataManager;
		this.placeController = placeController;
		this.tagActivityMapper = tagActivityMapper;
		this.eventBus = eventBus;
		initActivityManager();
	}

	private void initActivityManager() {
		ActivityManager activityManager = new ActivityManager(
				tagActivityMapper, eventBus);
		activityManager.setDisplay(tagView.getImageViewContainer());
	}

	public void setPlace(TagPlace place) {
		tagId = place.getTagId();
		photoId = place.getPhotoId();
	}

	@Override
	public void start(AcceptsOneWidget containerWidget, EventBus eventBus) {
		log.info("Start tag activity for tagId: " + tagId + "photoId: "
				+ photoId);
		tagView.setPresenter(this);
		containerWidget.setWidget(tagView);
	}

	protected void goToPhoto(String otherTagId, String photoId) {
		placeController.goTo(new TagPlace(otherTagId, photoId));
	}

	private void requestTagTreeData() {
		dataManager.getTagTree(new AsyncCallback<List<TagNode>>() {
			@Override
			public void onFailure(Throwable caught) {

			}

			@Override
			public void onSuccess(List<TagNode> result) {
				TreeItem treeModel = build(result);
				tagView.setTreeModel(treeModel);
			}
		});
	}

	private TreeItem build(List<TagNode> tagTrees) {
		TreeItem root = new TreeItem("");
		attach(root, tagTrees);
		return root;
	}

	private void attach(TreeItem item, List<TagNode> children) {
		for (TagNode child : children) {
			TreeItem newChild = item.addItem(child.getTagName());
			newChild.setUserObject(child);
			attach(newChild, child.getChildren());
		}
	}

	@Override
	public void treeSelectionChanged(SelectionEvent<TreeItem> event) {
		TreeItem selectedItem = event.getSelectedItem();
		if (selectedItem != null) {
			if (selectedItem.getParentItem() != null) {
				TagNode selectedTag = (TagNode) selectedItem.getUserObject();
				if (!selectedTag.getCachedPhotoList().isEmpty()) {
					String firstPhotoId = selectedTag.getCachedPhotoList().get(
							0);
					String tagId = selectedTag.getId();
					goToPhoto(tagId, firstPhotoId);
				}
			}
		}
	}

	@Override
	public void reloadTree() {
		requestTagTreeData();
	}
}
