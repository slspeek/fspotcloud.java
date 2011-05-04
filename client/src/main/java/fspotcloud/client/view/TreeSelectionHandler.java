package fspotcloud.client.view;

import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SelectionChangeEvent.Handler;
import com.google.gwt.view.client.SingleSelectionModel;
import com.google.inject.Inject;

import fspotcloud.shared.tag.TagNode;

public class TreeSelectionHandler implements Handler {

	final private SingleSelectionModel<TagNode> selectionModel;
	final private PlaceGoTo placeGoTo;

	@Inject
	public TreeSelectionHandler(SingleSelectionModel<TagNode> selectionModel,
			PlaceGoTo placeGoTo) {
		this.selectionModel = selectionModel;
		this.placeGoTo = placeGoTo;
	}

	@Override
	public void onSelectionChange(SelectionChangeEvent event) {
		TagNode node = selectionModel.getSelectedObject();
		if (node != null) {
			if (!node.getCachedPhotoList().isEmpty()) {
				String firstPhotoId = node.getCachedPhotoList().get(0).getId();
				String tagId = node.getId();
				goToPhoto(tagId, firstPhotoId);
			}
		}
	}
	
	private void goToPhoto(String otherTagId, String photoId) {
		placeGoTo.goTo(new TagViewingPlace(otherTagId, photoId));
	}
}
