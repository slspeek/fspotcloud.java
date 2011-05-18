package fspotcloud.client.main;

public class HelpContentGenerator {

	public String getHelpRow(String key, String altKey1, String altKey2,
			String description) {
		String row = "<tr><td>";
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
				+ "</span></td>";
		row += "</tr>";
		return row;
	}
}
