package fspotcloud.client.main.view;

import com.google.inject.Inject;

import fspotcloud.client.main.help.HelpContentGenerator;
import fspotcloud.client.main.ui.Resources;
import fspotcloud.client.main.view.api.PopupView;
import fspotcloud.client.view.action.api.AboutActions;
import fspotcloud.client.view.action.api.ActionGroup;
import fspotcloud.client.view.action.api.UserAction;

public class AboutPresenter implements PopupView.PopupPresenter {

	final private PopupView popupView;
	final private HelpContentGenerator generator;
	private String helptext;
	private final AboutActions actions;
	private Resources resources;

	@Inject
	public AboutPresenter(AboutActions actions, HelpContentGenerator generator, PopupView popupView, Resources resources) {
		this.actions = actions;
		this.resources = resources;
		this.generator = generator;
		this.popupView = popupView;
		popupView.setText(initHelpText());
		popupView.setTitle("About");
	}

	private String initHelpText() {
		helptext = "<table>";
		helptext += getAboutGroup(actions);
		
		helptext += "<tr><td colspan=4>";
		String version = resources.getVersion().getText(); 
		helptext += version.replaceAll("\n", "<br/>");
		helptext += "</td></tr>";
		
		helptext += "</table>";
		return helptext;
	}

	private String getAboutGroup(ActionGroup group) {
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
	}

	public void hide() {
		popupView.hide();
	}
}
