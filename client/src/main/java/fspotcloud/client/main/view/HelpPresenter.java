package fspotcloud.client.main.view;

import fspotcloud.client.main.HelpContentGenerator;
import fspotcloud.client.main.ui.HelpPopup;
import fspotcloud.client.view.action.AllShortcuts;
import fspotcloud.client.view.action.Shortcut;

public class HelpPresenter {

	final private HelpPopup popupView;
	final private HelpContentGenerator generator = new HelpContentGenerator();
	private String helptext;

	public HelpPresenter() {
		popupView = new HelpPopup(initHelpText());
	}

	private String initHelpText() {
		helptext = "<table>";
		for (Shortcut shortcut : AllShortcuts.all) {
			helptext += "<tr><td>" + generator.getHelpText(shortcut) + "</td></tr>";
		}
			helptext += "</table>";
		return helptext;
	}

	public void show() {
		popupView.setGlassEnabled(true);
		popupView.center();
		popupView.show();
	}

	public void hide() {
		popupView.hide();
	}
}
