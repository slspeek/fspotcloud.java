package fspotcloud.client.main.view.api;

public interface PopupView {

	interface PopupPresenter {
		void hide();

		void show();

	}

	void setText(String text);
	
	void setTitle(String text);

	void hide();

	void show();

	void setGlassEnabled(boolean enabled);

	void center();

	void setPopupPosition(int i, int j);
	
	void focus();

}
