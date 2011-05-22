package fspotcloud.client.admin.view;

import fspotcloud.client.admin.view.api.GlobalActionsView;
import fspotcloud.rpc.TagServiceAsync;

public class GlobalActionsPresenter implements GlobalActionsView.GlobalActionsPresenter {

	final private TagServiceAsync tagServiceAsync;
	final private GlobalActionsView globalActionsView;

	public GlobalActionsPresenter(TagServiceAsync tagServiceAsync,
			GlobalActionsView globalActionsView) {
		super();
		this.tagServiceAsync = tagServiceAsync;
		this.globalActionsView = globalActionsView;
	}

	@Override
	public void deleteAllPhotos() {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteAllTags() {
		// TODO Auto-generated method stub

	}

	@Override
	public void importTags() {
		// TODO Auto-generated method stub

	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

}
