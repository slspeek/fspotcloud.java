package com.googlecode.fspotcloud.client.main.view.api;

import com.google.gwt.view.client.SingleSelectionModel;
import com.googlecode.fspotcloud.shared.tag.TagNode;


public interface TreeSelectionHandlerInterface extends
		com.google.gwt.view.client.SelectionChangeEvent.Handler {
	void setSelectionModel(SingleSelectionModel<TagNode> selectionModel);
}
