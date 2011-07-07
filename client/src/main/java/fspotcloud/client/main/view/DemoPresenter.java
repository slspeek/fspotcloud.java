package fspotcloud.client.main.view;

import fspotcloud.client.main.ui.DemoPopup;

public class DemoPresenter {

	final private DemoPopup popupView;
	final private String text;

	public DemoPresenter(String text) {
		this.text = text;
		popupView = new DemoPopup(initText());
	}

	private String initText() {
		String helptext = "<table>";
		helptext += text;
		helptext += "</table>";
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
