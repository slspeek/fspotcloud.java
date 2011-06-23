package fspotcloud.client.main.view;

import fspotcloud.client.main.HelpContentGenerator;
import fspotcloud.client.main.ui.HelpPopup;

public class HelpPresenter {

	final private HelpPopup popupView;
	final private HelpContentGenerator generator = new HelpContentGenerator();
	private String helptext;

	public HelpPresenter() {
		popupView = new HelpPopup(initHelpText());
	}

	private String initHelpText() {
		helptext = "<table>";
		helptext += generator.getHelpRow("t", null, null,
				"Toggle single/tabular image view");
		helptext += generator.getHelpRow("f", "1", null,
				"Toggle fullscreen (hides/shows the tree view)");
		helptext += generator.getHelpRow("ENTER", null, null,
				"Puts keyboard focus on the category tree");
		helptext += generator.getHelpRow("b", "HOME", null,
				"Go to the first image of the category");
		helptext += generator.getHelpRow("e", "z", "END",
				"Go to the last image of the category");
		helptext += generator.getHelpRow("p", "LEFT", null,
				"Previous image in this category");
		helptext += generator.getHelpRow("n", "RIGHT", null,
				"Next image in this category");
		helptext += generator.getHelpRow("s", "g", null, "Start slideshow");
		helptext += generator.getHelpRow("q", "x", null, "Stop slideshow");
		helptext += generator.getHelpRow("i", null, null,
				"Make the slideshow go faster");
		helptext += generator.getHelpRow("d", null, null,
				"Make the slideshow go slower");

		helptext += generator.getHelpRow("h", "ESCAPE", null,
				"Show/Hide keyboard shortcuts");
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
