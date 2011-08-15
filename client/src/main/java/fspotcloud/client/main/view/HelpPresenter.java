package fspotcloud.client.main.view;

import com.google.inject.Inject;

import fspotcloud.client.main.help.HelpContentGenerator;
import fspotcloud.client.main.ui.HelpPopup;
import fspotcloud.client.view.action.api.ActionGroup;
import fspotcloud.client.view.action.api.AllUserActions;
import fspotcloud.client.view.action.api.UserAction;

public class HelpPresenter {

	final private HelpPopup popupView;
	final private HelpContentGenerator generator;
	private String helptext;
	private final AllUserActions actions;

	@Inject
	public HelpPresenter(AllUserActions actions, HelpContentGenerator generator, HelpPopup popupView) {
		this.actions = actions;
		this.generator = generator;
		this.popupView = popupView;
		popupView.setText(initHelpText());
	}

	private String initHelpText() {
		helptext = "<table>";
		helptext += "<tr><td>";
		helptext += getHelpGroup(actions.navigation());
		helptext += "</td><td>";
		helptext += getHelpGroup(actions.slideshow());
		helptext += "</td></tr><tr><td>";
		helptext += getHelpGroup(actions.raster());
		helptext += "</td><td>";
		helptext += getHelpGroup(actions.application());
		helptext += "</td></tr>";
		helptext += "</table>";
		return helptext;
	}

	private String getHelpGroup(ActionGroup group) {
		String title = group.getDescription();
		String helptext = "<table>";
		helptext += "<tr><th colspan=3 >";
		helptext += title;
		helptext += "</tr></th>";
		for (UserAction shortcut : group.allActions()) {
			helptext += "<tr><td>" + generator.getHelpText(shortcut)
					+ "</td></tr>";
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
