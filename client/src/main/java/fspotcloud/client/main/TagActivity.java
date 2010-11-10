package fspotcloud.client.main;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

public class TagActivity extends AbstractActivity implements
		TagView.TagPresenter {
	String tagId;
	String photoId;
	List<String> photoList = new ArrayList<String>();
	EventBus eventBus;
	ClientFactory clientFactory;

	public TagActivity(TagPlace place, ClientFactory clientFactory) {
		tagId = place.getTagId();
		photoId = place.getPhotoId();
		this.clientFactory = clientFactory;
		requestKeysForTag(tagId);
	}

	@Override
	public void start(AcceptsOneWidget containerWidget, EventBus eventBus) {
		this.eventBus = eventBus;
		TagView tagView = clientFactory.getTagView();
		tagView.setPresenter(this);
		if (photoId != null) {
			tagView.setMainImageUrl("/image?id=" + photoId);
		}
		tagView.setTagId(tagId);
		containerWidget.setWidget(tagView);
	}

	@Override
	public boolean canGoBackward() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canGoForward() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void goBackward() {
		// TODO Auto-generated method stub

	}

	@Override
	public void goFirst() {
		if (!photoList.isEmpty()) {
			clientFactory.getPlaceController().goTo(
					new TagPlace(tagId, photoList.get(0)));
		}
	}

	@Override
	public void goForward() {
		// TODO Auto-generated method stub

	}

	@Override
	public void goLast() {
		// TODO Auto-generated method stub

	}

	private void requestKeysForTag(final String tagId) {
		clientFactory.getTagService().keysForTag(tagId,
				new AsyncCallback<List<String>>() {

					TagView tagView = clientFactory.getTagView();

					public void onFailure(Throwable caught) {
						tagView
								.setStatusText("Error recieving photo list for id: "
										+ tagId + ".");
					}

					public void onSuccess(List<String> result) {
						tagView.setStatusText("Photo list recieved.");
						photoList = result;
						if (photoId == null) {
							goFirst();
						}
					}
				});
	}

}
