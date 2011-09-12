package fspotcloud.client.main.view;

import com.google.inject.Inject;

import fspotcloud.client.main.event.ActionFamily;
import fspotcloud.client.main.event.ActionMap;
import fspotcloud.client.main.help.HelpContentGenerator;
import fspotcloud.client.main.view.api.PopupView;
import fspotcloud.client.view.action.api.UserAction;

public class HelpPresenter implements PopupView.PopupPresenter {

	final private PopupView popupView;
	final private HelpContentGenerator generator;
	private String helptext;
	private final ActionFamily actions;

	@Inject
	public HelpPresenter(ActionFamily actions, HelpContentGenerator generator, PopupView popupView) {
		this.actions = actions;
		this.generator = generator;
		this.popupView = popupView;
		popupView.setText(initHelpText());
		popupView.setTitle("Keyboard Shortcuts");
	}

	private String initHelpText() {
		helptext = "<table>";
		helptext += "<tr><td>";
		helptext += getHelpGroup(actions.get("Navigation"));
		helptext += "</td><td>";
		helptext += getHelpGroup(actions.get("Slideshow"));
		helptext += "</td></tr><tr><td>";
		helptext += getHelpGroup(actions.get("Raster"));
		helptext += "</td><td>";
		helptext += getHelpGroup(actions.get("Application"));
		helptext += "</td></tr>";
		helptext += "</table>";
		return helptext;
	}

	private String getHelpGroup(ActionMap group) {
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
		popupView.focus();
	}

	public void hide() {
		popupView.hide();
	}
}
