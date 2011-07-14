package fspotcloud.client.view.action;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.resources.client.ImageResource;
import com.google.inject.Inject;

import fspotcloud.client.main.shared.NavigationEvent;
import fspotcloud.client.main.shared.SlideshowEvent;
import fspotcloud.client.view.action.api.AllUserActions;
import fspotcloud.client.view.action.api.UserAction;

public class AllShortcuts implements AllUserActions {
	private static final Logger log = Logger.getLogger(AllUserActions.class
			.getName());
	public UserAction TOGGLE_TABULAR_VIEW;
	public UserAction TOGGLE_FULLSCREEN;
	public UserAction TREE_FOCUS;
	public UserAction ADD_COLUMN;
	public UserAction ADD_ROW;
	public UserAction REMOVE_COLUMN;
	public UserAction REMOVE_ROW;
	public UserAction SET_DEFAULT_RASTER;
	public UserAction SET_RASTER_2x2;
	public UserAction SET_RASTER_3x3;
	public UserAction SET_RASTER_4x4;
	public UserAction SET_RASTER_5x5;
	public UserAction GOTO_START;
	public UserAction GOTO_END;
	public UserAction GO_BACK;
	public UserAction GO_FORWARD;
	public UserAction SLIDESHOW_START;
	public UserAction SLIDESHOW__END;
	public UserAction SLIDESHOW_SLOWER;
	public UserAction SLIDESHOW_FASTER;
	public UserAction TOGGLE_HELP;
	public UserAction START_DEMO;

	private ShortcutFactory factory;
	private ToggleFullscreenAction toggleFullscreen;
	private TreeFocusAction treeFocusAction;

	public List<UserAction> all = Arrays.asList(TOGGLE_FULLSCREEN,
			TOGGLE_TABULAR_VIEW, TREE_FOCUS, ADD_COLUMN, ADD_ROW,
			REMOVE_COLUMN, REMOVE_ROW, SET_DEFAULT_RASTER, SET_RASTER_2x2,
			SET_RASTER_3x3, SET_RASTER_4x4, SET_RASTER_5x5, GOTO_START,
			GOTO_END, GO_BACK, GO_FORWARD, SLIDESHOW_START, SLIDESHOW__END,
			SLIDESHOW_SLOWER, SLIDESHOW_FASTER, TOGGLE_HELP, START_DEMO);

	@Inject
	public AllShortcuts(ShortcutFactory factory, ToggleFullscreenAction toggleFullscreen,
			TreeFocusAction treeFocusAction) {
		super();
		this.factory = factory;
		this.treeFocusAction = treeFocusAction;
		this.toggleFullscreen = toggleFullscreen;
		init();
		log.info("created after init");
	}

	public void init() {
		initApplicationActions();
		initMovementActions();
		initRasterActions();
		initSlideshowActions();
	}

	private void initSlideshowActions() {
		SLIDESHOW_START = createSlideshow("Play", 'S', (int) 'G', "Start slideshow",
				null, SlideshowEvent.ACTION_START);
		SLIDESHOW__END = createSlideshow("Stop", 'Q', null, "Stop slideshow",
				null, SlideshowEvent.ACTION_STOP);
		SLIDESHOW_SLOWER = createSlideshow("Slower", 'U', null,
				"Makes the slideshow go slower", null,
				SlideshowEvent.ACTION_SLOWER);
		SLIDESHOW_FASTER = createSlideshow("Faster", 'I', null,
				"Makes the slideshow go faster", null,
				SlideshowEvent.ACTION_FASTER);
	}

	private void initMovementActions() {
		GOTO_START = createNavigation("Home", 'B', KeyCodes.KEY_HOME,
				"Go to the first image of the category", null,
				NavigationEvent.BEGIN);
		GOTO_END = createNavigation("End", KeyCodes.KEY_END, null,
				"Go to the last image of the category", null,
				NavigationEvent.END);
		GO_BACK = createNavigation("Back", 'N', KeyCodes.KEY_LEFT,
				"Previous image in this category", null, NavigationEvent.BACK);
		GO_FORWARD = createNavigation("Next", 'M', KeyCodes.KEY_RIGHT,
				"Next image in this category", null, NavigationEvent.FORWARD);
	}

	private void initRasterActions() {
		ADD_COLUMN = createNavigation("Add column", 'C', null,
				"Adds one column to raster", null,
				NavigationEvent.INCREASE_RASTER_WIDTH);
		ADD_ROW = createNavigation("Add row", 'R', null,
				"Adds one row to raster", null, NavigationEvent.INCREASE_RASTER_WIDTH);
		REMOVE_COLUMN = createNavigation("Remove column", 'X', null,
				"Removes one column from the raster", null, NavigationEvent.DECREASE_RASTER_WIDTH);
		REMOVE_ROW = createNavigation("Remove row", 'E', null,
				"Removes one row from the raster",null, NavigationEvent.DECREASE_RASTER_HEIGHT);
		SET_DEFAULT_RASTER = createNavigation("Reset raster", '0',
				null, "Resets raster to defaults",null, NavigationEvent.SET_DEFAULT_RASTER);
		SET_RASTER_2x2 = createNavigation("2x2", '2', null,
			"Sets the raster to 2 x 2",null, NavigationEvent.SET_RASTER_2x2);
		SET_RASTER_3x3 = createNavigation("3x3", '3', null,
			"Sets the raster to 3 x 3",null, NavigationEvent.SET_RASTER_3x3);
		SET_RASTER_4x4 = createNavigation("4x4", '4', null,
				"Sets the raster to 4 x 4",null, NavigationEvent.SET_RASTER_4x4);
		SET_RASTER_5x5 = createNavigation("5x5", '5', null,
				"Sets the raster to 5 x 5",null, NavigationEvent.SET_RASTER_5x5);
	}

	private void initApplicationActions() {
		TOGGLE_HELP = SET_DEFAULT_RASTER;
		START_DEMO = SET_DEFAULT_RASTER;
		TREE_FOCUS = new Shortcut("Focus tree",
				"Puts keyboard focus on the category tree", new KeyStroke(
						KeyCodes.KEY_ENTER), null, null, treeFocusAction);
		TOGGLE_FULLSCREEN = new Shortcut("Show/Hide tree",
				"Toggles fullscreen (hides/shows the tree view)",
				new KeyStroke('F'), null, null, toggleFullscreen);
	}

	public UserAction createSlideshow(String caption, int key, Integer altKey,
			String description, ImageResource icon, int actionType) {
		return factory.getSlideshow(caption, key, altKey, description,
				actionType);

	}

	public UserAction createNavigation(String caption, int key, Integer altKey,
			String description, ImageResource icon, int actionType) {
		return factory.getNavigation(caption, key, altKey, description,
				actionType);
	}

	@Override
	public UserAction back() {
		return GO_BACK;
	}

	@Override
	public UserAction next() {
		return GO_FORWARD;
	}

	@Override
	public UserAction toggleTabularView() {
		return TOGGLE_TABULAR_VIEW;
	}

	@Override
	public UserAction toggleTreeVisible() {
		return TOGGLE_FULLSCREEN;
	}

	@Override
	public UserAction treeFocus() {
		return TREE_FOCUS;
	}

	@Override
	public UserAction addColumm() {
		return ADD_COLUMN;
	}

	@Override
	public UserAction addRow() {
		return ADD_ROW;
	}

	@Override
	public UserAction removeColumn() {
		return REMOVE_COLUMN;
	}

	@Override
	public UserAction removeRow() {
		return REMOVE_ROW;
	}

	@Override
	public UserAction resetRaster() {
		return SET_DEFAULT_RASTER;
	}

	@Override
	public UserAction setRaster2x2() {
		return SET_RASTER_2x2;
	}

	@Override
	public UserAction setRaster3x3() {
		return SET_RASTER_3x3;
	}

	@Override
	public UserAction setRaster4x4() {
		return SET_RASTER_4x4;
		}

	@Override
	public UserAction setRaster5x5() {
		return SET_RASTER_5x5;
	}

	@Override
	public UserAction home() {
		return GOTO_START;
	}

	@Override
	public UserAction end() {
		return SLIDESHOW__END;
	}

	@Override
	public UserAction startSlideshow() {
		return SLIDESHOW_START;

	}

	@Override
	public UserAction stopSlideshow() {
		return SLIDESHOW__END;
	}

	@Override
	public UserAction slower() {
		return SLIDESHOW_SLOWER;
	}

	@Override
	public UserAction faster() {
		return SLIDESHOW_FASTER;
	}

	@Override
	public UserAction toggleHelp() {
		return SLIDESHOW_FASTER;//TOGGLE_HELP;
	}

	@Override
	public UserAction demo() {
		return SLIDESHOW__END;//START_DEMO;
	}

	@Override
	public List<UserAction> allActions() {
		return all;
	}

}
