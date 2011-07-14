package fspotcloud.client.main;

import fspotcloud.client.view.action.KeyStroke;
import fspotcloud.client.view.action.api.UserAction;

public class HelpContentGenerator {

	private String getHelpRow(String key, String altKey1, String altKey2,
			String description) {
		String row = "";
		row += "<span class='fsc-help-key'>" + key + "</span>";
		if (altKey1 != null) {
			row += " or <span class='fsc-help-key'>" + altKey1 + "</span>";
			if (altKey2 != null) {
				row += " or <span class='fsc-help-key'>" + altKey2 + "</span>";
			}
		}
		row += "</td>";

		row += "<td><span class='fsc-help-separator'>:</span></td>";
		row += "<td><span class='fsc-help-description'>" + description
				+ "</span>";
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
			return getHelpRow(key, altKey, null, shortcut.getDescription());
		} else {
			return getHelpRow(key, null, null, shortcut.getDescription());
		}
	}
}
