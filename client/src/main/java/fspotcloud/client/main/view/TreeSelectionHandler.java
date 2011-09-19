package fspotcloud.client.main.view;

import java.util.logging.Logger;

import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;
import com.google.inject.Inject;

import fspotcloud.client.main.view.api.TreeSelectionHandlerInterface;
import fspotcloud.client.place.api.Navigator;
import fspotcloud.shared.photo.PhotoInfoStore;
import fspotcloud.shared.tag.TagNode;

public class TreeSelectionHandler implements TreeSelectionHandlerInterface {
	private static final Logger log = Logger
			.getLogger(TreeSelectionHandler.class.getName());
	private SingleSelectionModel<TagNode> selectionModel;

	final private Navigator navigator;

	@Inject
	public TreeSelectionHandler(Navigator navigator) {
		this.navigator = navigator;
	}

	public void setSelectionModel(SingleSelectionModel<TagNode> selectionModel) {
		this.selectionModel = selectionModel;
		selectionModel.addSelectionChangeHandler(this);
	}

	@Override
	public void onSelectionChange(SelectionChangeEvent event) {
		log.info("Selection event from tree" + selectionModel);
		TagNode node = selectionModel.getSelectedObject();
		if (node != null) {
			String tagId = node.getId();
			goToPhoto(tagId, node.getCachedPhotoList());
		}
	}

	private void goToPhoto(String otherTagId, PhotoInfoStore store) {
		navigator.goToTag(otherTagId, store);
	}


}
