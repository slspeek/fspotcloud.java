package fspotcloud.client.main.view;

import com.google.inject.Inject;
import com.google.inject.name.Named;

import fspotcloud.client.main.event.AbstractActionMap;
import fspotcloud.client.main.event.ActionMap;
import fspotcloud.client.main.help.HelpContentGenerator;
import fspotcloud.client.main.ui.Resources;
import fspotcloud.client.main.view.api.PopupView;
import fspotcloud.client.view.action.api.UserAction;

public class AboutPresenter implements PopupView.PopupPresenter {

	final private PopupView popupView;
	final private HelpContentGenerator generator;
	private String helptext;
	private final ActionMap actions;
	private Resources resources;

	@Inject
	public AboutPresenter(@Named("about") AbstractActionMap actions, HelpContentGenerator generator, PopupView popupView, Resources resources) {
		this.actions = actions;
		actions.buildMap();
		this.resources = resources;
		this.generator = generator;
		this.popupView = popupView;
		popupView.setText(initHelpText());
		popupView.setTitle("About");
	}

	private String initHelpText() {
		helptext = "<table style='text-align: left'>";
		helptext += getAboutGroup(actions);
		
		helptext += "<tr><td colspan=4 style='text-align: center'>";
		String version = resources.getVersion().getText(); 
		helptext += version.replaceAll("\n", "<br/>");
		helptext += "</td></tr>";
		
		helptext += "</table>";
		return helptext;
	}

	private String getAboutGroup(ActionMap group) {
		for (UserAction shortcut : group.allActions()) {
			helptext += "<tr><td>" + generator.getHelpText(shortcut)
					+ "</td></tr>";
		}
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
