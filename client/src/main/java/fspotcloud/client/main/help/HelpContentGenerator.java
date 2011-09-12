package fspotcloud.client.main.help;

import com.google.gwt.resources.client.ImageResource;
import com.google.inject.Inject;

import fspotcloud.client.main.ui.Resources;
import fspotcloud.client.view.action.KeyStroke;
import fspotcloud.client.view.action.api.UserAction;

public class HelpContentGenerator {

	final private Resources.Style style;

	@Inject
	public HelpContentGenerator(Resources res) {
		super();
		this.style = res.style();
	}

	private String getHelpRow(String key, String altKey1, String altKey2,
			String description, ImageResource icon) {
		String row = "";
		row += "<span class='" + style.helpKey() + "'>" + key + "</span>";
		if (altKey1 != null) {
			row += " or <span class='" + style.helpKey() + "'>" + altKey1
					+ "</span>";
			if (altKey2 != null) {
				row += " or <span class='" + style.helpKey() + "'>" + altKey2
						+ "</span>";
			}
		}
		row += "</td>";

		row += "<td><span class='" + style.helpSeparator() + "'>:</span></td>";
		row += "<td>";
		if (icon != null) {
			row += "<img src='" + icon.getURL() + "' />";
		}
		row += "</td>";
		row += "<td><span class='" + style.helpDescription() + "'>"
				+ description + "</span>";
		return row;
	}

	public String getHelpText(UserAction shortcut) {
		String key, altKey;
		KeyStroke stroke, altStroke;
		stroke = shortcut.getKey();
		altStroke = shortcut.getAlternateKey();
		key = stroke.getKeyString();
		if (altStroke != null) {
			altKey = altStroke.getKeyString();
			return getHelpRow(key, altKey, null, shortcut.getDescription(),
					shortcut.getIcon());

		} else {
			return getHelpRow(key, null, null, shortcut.getDescription(),
					shortcut.getIcon());
		}
	}
}
