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
		helptext += generator.getHelpRow("t", "1", null,
				"Toggle single/tabular image view");
		helptext += generator.getHelpRow("f", null, null,
				"Toggle fullscreen (hides/shows the tree view)");
		helptext += generator.getHelpRow("ENTER", null, null,
				"Puts keyboard focus on the category tree");
		helptext += generator.getHelpRow("c", null, null,
		"Adds one column to raster");
		helptext += generator.getHelpRow("x", null, null,
		"Removes one column from the raster");
		helptext += generator.getHelpRow("r", null, null,
		"Adds one row to raster");
		helptext += generator.getHelpRow("e", null, null,
		"Removes one row from the raster");
		helptext += generator.getHelpRow("d", null, null,
		"Make the slideshow go slower");
		helptext += generator.getHelpRow("d", null, null,
		"Make the slideshow go slower");
		helptext += generator.getHelpRow("2", null, null,
		"Sets the raster to 2 x 2");
		helptext += generator.getHelpRow("3", null, null,
		"Sets the raster to 3 x 3");
		helptext += generator.getHelpRow("4", null, null,
		"Sets the raster to 4 x 4");
		helptext += generator.getHelpRow("5", null, null,
		"Sets the raster to 5 x 5");
		helptext += generator.getHelpRow("0", null, null,
		"Reset to raster to its defaults");
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
