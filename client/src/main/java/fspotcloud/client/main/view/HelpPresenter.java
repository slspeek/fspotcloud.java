package fspotcloud.client.main.view;

import fspotcloud.client.main.HelpContentGenerator;
import fspotcloud.client.main.ui.HelpPopup;
import fspotcloud.client.view.action.api.AllUserActions;
import fspotcloud.client.view.action.api.UserAction;

public class HelpPresenter {

	final private HelpPopup popupView;
	final private HelpContentGenerator generator = new HelpContentGenerator();
	private String helptext;
	private final AllUserActions actions;

	
	public HelpPresenter(AllUserActions actions) {
		this.actions = actions;
		popupView = new HelpPopup(initHelpText());
	}

	private String initHelpText() {
		helptext = "<table>";
		for (UserAction shortcut : actions.allActions()) {
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
