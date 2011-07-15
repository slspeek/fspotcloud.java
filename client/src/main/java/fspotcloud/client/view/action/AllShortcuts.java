package fspotcloud.client.view.action;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.resources.client.ImageResource;
import com.google.inject.Inject;
import com.google.inject.Provider;

import fspotcloud.client.main.shared.ApplicationEvent;
import fspotcloud.client.main.shared.ApplicationEventProviderFactory;
import fspotcloud.client.main.shared.NavigationEvent;
import fspotcloud.client.main.shared.NavigationEventProviderFactory;
import fspotcloud.client.main.shared.SlideshowEvent;
import fspotcloud.client.main.shared.SlideshowEventProviderFactory;
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
	public UserAction START_DEMO; // 22 = 4 + 4 + 10 + 4

	private ShortcutAssistedFactory factory;
	private NavigationEventProviderFactory navigation;
	private ApplicationEventProviderFactory application;
	private SlideshowEventProviderFactory slideshow;

	public List<UserAction> all;

	@Inject
	public AllShortcuts(ShortcutAssistedFactory factory,
			NavigationEventProviderFactory navigation,
			ApplicationEventProviderFactory application,
			SlideshowEventProviderFactory slideshow) {
		super();
		this.factory = factory;
		this.navigation = navigation;
		this.application = application;
		this.slideshow = slideshow;
		init();
		log.info("created and after init");
	}

	public void init() {
		initMovementActions();
		initRasterActions();
		initSlideshowActions();
		initApplicationActions();
		all = Arrays.asList(TOGGLE_FULLSCREEN, TOGGLE_TABULAR_VIEW, TREE_FOCUS,
				ADD_COLUMN, ADD_ROW, REMOVE_COLUMN, REMOVE_ROW,
				SET_DEFAULT_RASTER, SET_RASTER_2x2, SET_RASTER_3x3,
				SET_RASTER_4x4, SET_RASTER_5x5, GOTO_START, GOTO_END, GO_BACK,
				GO_FORWARD, SLIDESHOW_START, SLIDESHOW__END, SLIDESHOW_SLOWER,
				SLIDESHOW_FASTER, TOGGLE_HELP, START_DEMO);
	}

	private void initSlideshowActions() { // 4
		SLIDESHOW_START = createSlideshow("Play", 'S', (int) 'G',
				"Start slideshow", null, SlideshowEvent.ACTION_START);
		SLIDESHOW__END = createSlideshow("Stop", 'Q', null, "Stop slideshow",
				null, SlideshowEvent.ACTION_STOP);
		SLIDESHOW_SLOWER = createSlideshow("Slower", 'U', null,
				"Makes the slideshow go slower", null,
				SlideshowEvent.ACTION_SLOWER);
		SLIDESHOW_FASTER = createSlideshow("Faster", 'I', null,
				"Makes the slideshow go faster", null,
				SlideshowEvent.ACTION_FASTER);
	}

	private void initMovementActions() { // 4
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

	private void initRasterActions() { // 10
		ADD_COLUMN = createNavigation("Add column", 'C', null,
				"Adds one column to raster", null,
				NavigationEvent.INCREASE_RASTER_WIDTH);
		ADD_ROW = createNavigation("Add row", 'R', null,
				"Adds one row to raster", null,
				NavigationEvent.INCREASE_RASTER_WIDTH);
		REMOVE_COLUMN = createNavigation("Remove column", 'X', null,
				"Removes one column from the raster", null,
				NavigationEvent.DECREASE_RASTER_WIDTH);
		REMOVE_ROW = createNavigation("Remove row", 'E', null,
				"Removes one row from the raster", null,
				NavigationEvent.DECREASE_RASTER_HEIGHT);
		SET_DEFAULT_RASTER = createNavigation("Reset raster", '0', null,
				"Resets raster to defaults", null,
				NavigationEvent.SET_DEFAULT_RASTER);
		SET_RASTER_2x2 = createNavigation("2x2", '2', null,
				"Sets the raster to 2 x 2", null,
				NavigationEvent.SET_RASTER_2x2);
		SET_RASTER_3x3 = createNavigation("3x3", '3', null,
				"Sets the raster to 3 x 3", null,
				NavigationEvent.SET_RASTER_3x3);
		SET_RASTER_4x4 = createNavigation("4x4", '4', null,
				"Sets the raster to 4 x 4", null,
				NavigationEvent.SET_RASTER_4x4);
		SET_RASTER_5x5 = createNavigation("5x5", '5', null,
				"Sets the raster to 5 x 5", null,
				NavigationEvent.SET_RASTER_5x5);
		TOGGLE_TABULAR_VIEW = createNavigation("Toggle raster", 'T', null,
				"Toggle tabular viewing", null,
				NavigationEvent.TOGGLE_RASTER_VIEW);
	}

	private void initApplicationActions() { // 4
		TOGGLE_HELP = createApplication("Help",
				"Display a popup with the keyboard shortcuts", 'H',
				KeyCodes.KEY_ESCAPE, null, ApplicationEvent.ACTION_HELP);
		START_DEMO = createApplication("Demo", "Play a demo", '7', null, null,
				ApplicationEvent.ACTION_DEMO);
		TREE_FOCUS = createApplication("Focus tree",
				"Puts keyboard focus on the category tree", KeyCodes.KEY_ENTER,
				null, null, ApplicationEvent.ACTION_TREE_FOCUS);
		TOGGLE_FULLSCREEN = createApplication("Show/Hide tree",
				"Toggles fullscreen (hides/shows the tree view)", 'F', null,
				null, ApplicationEvent.ACTION_TOGGLE_TREE_VISIBLE);
	}

	public UserAction createApplication(String caption, String description,
			int key, Integer altKey, ImageResource icon, int actionType) {
		return create(caption, key, altKey, description, icon, application.get(actionType));
	}

	public UserAction createSlideshow(String caption, int key, Integer altKey,
			String description, ImageResource icon, int actionType) {
		return create(caption, key, altKey, description, icon, slideshow.get(actionType));
	}

	public UserAction createNavigation(String caption, int key, Integer altKey,
			String description, ImageResource icon, int actionType) {
		log.info("create Navigation " + caption);
		return create(caption, key, altKey, description, icon, navigation.get(actionType));
	}

	private UserAction create(String caption, int key, Integer altKey,
			String description, ImageResource icon, Provider<? extends GwtEvent> provider) {
		UserAction userAction;
		if (altKey != null) {
			userAction = factory.get(caption, description, new KeyStroke(key),
					new KeyStroke(altKey), icon, provider);
		} else {
			userAction = factory.get(caption, description, new KeyStroke(key),
					null, icon, provider);
		}
		return userAction;
		
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
		return TOGGLE_HELP;
	}

	@Override
	public UserAction demo() {
		return START_DEMO;
	}

	@Override
	public List<UserAction> allActions() {
		return all;
	}

}
