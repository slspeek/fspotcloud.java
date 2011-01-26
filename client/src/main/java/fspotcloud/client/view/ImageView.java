package fspotcloud.client.view;

import com.google.gwt.user.client.ui.IsWidget;

public interface ImageView extends IsWidget {

	void setPresenter(ImagePresenter presenter);
	
	public interface ImagePresenter {
		public void goLast();
		public void goFirst();
		public void goForward();
		public void goBackward();
		public boolean canGoForward();
		public boolean canGoBackward();
		public void toggleSlideshow();
		public void setView(ImageView imageView);
	}

	void setImageUrl(String url);

	void setSlideshowButtonCaption(String caption);
	
}
