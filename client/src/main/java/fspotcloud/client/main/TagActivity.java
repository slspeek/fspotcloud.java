package fspotcloud.client.main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.TreeItem;

import fspotcloud.shared.tag.TagNode;

public class TagActivity extends AbstractActivity implements
		TagView.TagPresenter {
	private static final Logger log = Logger.getLogger(TagActivity.class
			.getName());

	String tagId;
	String photoId;
	Integer offset = null;
	List<String> photoList = null;
	final ClientFactory clientFactory;
	final DataManager dataManager;
	final TagView tagView;

	private boolean slideshowRunning = false;
	private Timer slideshowTimer = new Timer() {
		public void run() {
			log.info("Slideshow timer about to call goForward");
			goForward();
		}
	};

	public TagActivity(ClientFactory clientFactory) {
		this.clientFactory = clientFactory;
		this.tagView = clientFactory.getTagView();
		this.dataManager = clientFactory.getDataManager();
	}

	public void setPlace(TagPlace place) {
		tagId = place.getTagId();
		photoId = place.getPhotoId();
		TagNode node = dataManager.getTagNode(tagId);
		if (node != null) {
			photoList = node.getCachedPhotoList();
		} else {
			photoList = Collections.emptyList();
			log.warning("No information found for tagId: " + tagId);
		}
		int where = photoList.indexOf(photoId);
		if (where == -1) {
			if (!photoList.isEmpty()) {
				photoId = photoList.get(0);
				offset = 0;
			} else {
				offset = -1;
			}
		} else {
			offset = where;
		}
		if (photoId != null) {
			tagView.setMainImageUrl("/image?id=" + photoId);
		} else {
			log.warning("No photoId defined for tagId:  " + tagId);
		}
	}

	@Override
	public void start(AcceptsOneWidget containerWidget, EventBus eventBus) {
		log.info("Start for tagId: " + tagId + "photoId: " + photoId);
		tagView.setPresenter(this);
		containerWidget.setWidget(tagView);
	}

	@Override
	public boolean canGoBackward() {
		return offset > 0;
	}

	@Override
	public boolean canGoForward() {
		return offset >= 0 && offset < photoList.size() - 1;
	}

	private void goToPhoto(String photoId) {
		clientFactory.getPlaceController().goTo(new TagPlace(tagId, photoId));
	}

	@Override
	public void goBackward() {
		if (!photoList.isEmpty() && canGoBackward()) {
			String photoId = photoList.get(offset - 1);
			goToPhoto(photoId);
		}
	}

	@Override
	public void goFirst() {
		if (!photoList.isEmpty()) {
			String photoId = photoList.get(0);
			goToPhoto(photoId);
		}
	}

	@Override
	public void goForward() {
		if (!photoList.isEmpty() && canGoForward()) {
			String photoId = photoList.get(offset + 1);
			goToPhoto(photoId);
		}
	}

	@Override
	public void goLast() {
		if (!photoList.isEmpty()) {
			String photoId = photoList.get(photoList.size() - 1);
			goToPhoto(photoId);
		}
	}

	private void requestTagTreeData() {
		clientFactory.getDataManager().getTagTree(
				new AsyncCallback<List<TagNode>>() {
					@Override
					public void onFailure(Throwable caught) {

					}

					@Override
					public void onSuccess(List<TagNode> result) {
						TreeItem treeModel = build(result);
						clientFactory.getTagView().setTreeModel(treeModel);
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
			TagNode selectedTag = (TagNode) selectedItem.getUserObject();
			String tagId = selectedTag.getId();
			String firstPhotoId = selectedTag.getCachedPhotoList().get(0);
			TagPlace newPlace = new TagPlace(tagId, firstPhotoId);
			clientFactory.getPlaceController().goTo(newPlace);
		}
	}

	@Override
	public void reloadTree() {
		requestTagTreeData();
	}

	@Override
	public void toggleSlideshow() {
		if (slideshowRunning) {
			tagView.setSlideshowButtonCaption("Start");
			tagView.setStatusText("Slideshow stopt.");
			slideshowTimer.cancel();
		} else {
			tagView.setSlideshowButtonCaption("Stop");
			slideshowTimer.scheduleRepeating(3000);
			tagView.setStatusText("Slideshow started.");
		}
		slideshowRunning = !slideshowRunning;
	}
}
