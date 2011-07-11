package fspotcloud.client.view.action;

import java.util.Arrays;
import java.util.List;

import com.google.gwt.event.dom.client.KeyCodes;

public class AllShortcuts {

	public final static Shortcut TOGGLE_TABULAR_VIEW = create('T', '1',
			"Toggles single/tabular image view");
	public final static Shortcut TOGGLE_FULLSCREEN = create('F', null,
			"Toggles fullscreen (hides/shows the tree view)");
	public final static Shortcut TREE_FOCUS = create3(KeyCodes.KEY_ENTER, null,
			"Puts keyboard focus on the category tree");
	public final static Shortcut ADD_COLUMN = create('C', null,
			"Adds one column to raster");
	public final static Shortcut ADD_ROW = create('R', null,
			"Adds one row to raster");
	public final static Shortcut REMOVE_COLUMN = create('X', null,
			"Removes one column from the raster");
	public final static Shortcut REMOVE_ROW = create('E', null,
			"Removes one row from the raster");
	public final static Shortcut SET_DEFAULT_RASTER = create('0', null,
			"Resets raster to defaults");
	public final static Shortcut SET_RASTER_2x2 = create('2', null,
			"Sets the raster to 2 x 2");
	public final static Shortcut SET_RASTER_3x3 = create('3', null,
			"Sets the raster to 3 x 3");
	public final static Shortcut SET_RASTER_4x4 = create('4', null,
			"Sets the raster to 4 x 4");
	public final static Shortcut SET_RASTER_5x5 = create('5', null,
			"Sets the raster to 5 x 5");
	public final static Shortcut GOTO_START = create2('B', KeyCodes.KEY_HOME,
			"Go to the first image of the category");
	public final static Shortcut GOTO_END = create3(KeyCodes.KEY_END, null,
			"Go to the last image of the category");
	public final static Shortcut GO_BACK = create2('N', KeyCodes.KEY_LEFT,
			"Previous image in this category");
	public final static Shortcut GO_FORWARD = create2('M', KeyCodes.KEY_RIGHT,
			"Next image in this category");
	public final static Shortcut SLIDESHOW_START = create('S', 'G',
			"Start slideshow");
	public final static Shortcut SLIDESHOW__END = create('Q', null,
			"Stop slideshow");
	public final static Shortcut SLIDESHOW_SLOWER = create('U', null,
			"Makes the slideshow go slower");
	public final static Shortcut SLIDESHOW_FASTER = create('I', null,
			"Makes the slideshow go faster");
	public final static Shortcut TOGGLE_HELP = create2('H',
			KeyCodes.KEY_ESCAPE, "Show/Hide keyboard shortcuts");
	public final static Shortcut START_DEMO = create('7',
			null, "Start a demo");

	
	public final static List<Shortcut> all = Arrays.asList(TOGGLE_FULLSCREEN,
			TOGGLE_TABULAR_VIEW, TREE_FOCUS, ADD_COLUMN, ADD_ROW,
			REMOVE_COLUMN, REMOVE_ROW, SET_DEFAULT_RASTER, SET_RASTER_2x2,
			SET_RASTER_3x3, SET_RASTER_4x4, SET_RASTER_5x5, GOTO_START,
			GOTO_END, GO_BACK, GO_FORWARD, SLIDESHOW_START, SLIDESHOW__END,
			SLIDESHOW_SLOWER, SLIDESHOW_FASTER, TOGGLE_HELP, START_DEMO);

	public static Shortcut create(char key, Character altKey, String description) {
		if (altKey == null) {
			return new Shortcut(description, new KeyStroke(key), null);
		} else {
			return new Shortcut(description, new KeyStroke(key), new KeyStroke(
					altKey));
		}
	}

	public static Shortcut create2(char key, int altKey, String description) {
		return new Shortcut(description, new KeyStroke(key), new KeyStroke(
				altKey));
	}

	public static Shortcut create3(int key, Integer altKey, String description) {
		if (altKey == null) {
			return new Shortcut(description, new KeyStroke(key), null);
		} else {
			return new Shortcut(description, new KeyStroke(key), new KeyStroke(
					altKey));
		}
	}
}
