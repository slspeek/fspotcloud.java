package fspotcloud.client.main.view;

import com.google.inject.Inject;

import fspotcloud.client.main.view.api.PopupView;

public class DemoPresenter {

	final private PopupView popupView;
	private String text;

	@Inject
	public DemoPresenter(PopupView popupView) {
		this.popupView = popupView;
		popupView.setTitle("Demo");
	}
	

	public void setText(String text) {
		this.text = text;
		popupView.setText(initText());
	}

	private String initText() {
		String helptext = "<table><tr><td>";
		helptext += text;
		helptext += "</td></tr></table>";
		return helptext;
	}

	public void show() {
		//popupView.setGlassEnabled(true);
		popupView.center();
		popupView.setPopupPosition(30,30);
		popupView.show();
	}

	public void hide() {
		popupView.hide();
	}
}
