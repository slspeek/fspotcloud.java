package fspotcloud.client.main;

import com.google.gwt.user.client.ui.IsWidget;

public interface TagView extends IsWidget {

	public void setPresenter(TagPresenter presenter);
	
	public interface TagPresenter {
		public void goLast();
		public void goFirst();
		public void goForward();
		public void goBackward();
		public boolean canGoForward();
		public boolean canGoBackward();
	}

	public void setTagId(String tagId);

	void setMainImageUrl(String url);

	public void setStatusText(String string);
}
