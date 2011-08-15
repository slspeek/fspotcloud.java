package fspotcloud.client.main.view;

import com.google.inject.Inject;

import fspotcloud.client.main.ui.HelpPopup;

public class DemoPresenter {

	final private HelpPopup popupView;
	private String text;

	@Inject
	public DemoPresenter(HelpPopup popupView) {
		this.popupView = popupView;
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
