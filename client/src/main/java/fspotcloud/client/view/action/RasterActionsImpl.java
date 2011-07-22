package fspotcloud.client.view.action;

import java.util.Arrays;
import java.util.List;

import com.google.gwt.resources.client.ImageResource;
import com.google.inject.Inject;

import fspotcloud.client.main.shared.RasterEvent;
import fspotcloud.client.main.shared.RasterEventProviderFactory;
import fspotcloud.client.view.action.api.RasterActions;
import fspotcloud.client.view.action.api.ShortcutAssistedFactory;
import fspotcloud.client.view.action.api.UserAction;

public class RasterActionsImpl extends ActionsFactory implements
		RasterActions {
	
	public UserAction TOGGLE_TABULAR_VIEW;
	public UserAction ADD_COLUMN;
	public UserAction ADD_ROW;
	public UserAction REMOVE_COLUMN;
	public UserAction REMOVE_ROW;
	public UserAction SET_DEFAULT_RASTER;
	public UserAction SET_RASTER_2x2;
	public UserAction SET_RASTER_3x3;
	public UserAction SET_RASTER_4x4;
	public UserAction SET_RASTER_5x5;
	private List<UserAction> all;

	final private RasterEventProviderFactory raster;

	@Inject
	public RasterActionsImpl(ShortcutAssistedFactory shortcutFactory,
			RasterEventProviderFactory raster) {
		super(shortcutFactory);
		this.raster = raster;
		init();
	}

	private void init() {
		ADD_COLUMN = createRaster("add_colum", "Add column", 'C', null,
				"Adds one column to raster", null,
				RasterEvent.ActionType.INCREASE_RASTER_WIDTH);
		ADD_ROW = createRaster("add-row", "Add row", 'R', null,
				"Adds one row to raster", null,
				RasterEvent.ActionType.INCREASE_RASTER_HEIGHT);
		REMOVE_COLUMN = createRaster("remove-column", "Remove column", 'X', null,
				"Removes one column from the raster", null,
				RasterEvent.ActionType.DECREASE_RASTER_WIDTH);
		REMOVE_ROW = createRaster("remove-row", "Remove row", 'E', null,
				"Removes one row from the raster", null,
				RasterEvent.ActionType.DECREASE_RASTER_HEIGHT);
		SET_DEFAULT_RASTER = createRaster("reset", "Reset raster", '0', null,
				"Resets raster to defaults", null,
				RasterEvent.ActionType.SET_DEFAULT_RASTER);
		SET_RASTER_2x2 = createRaster("2x2","2x2", '2', null,
				"Sets the raster to 2 x 2", null,
				RasterEvent.ActionType.SET_RASTER_2x2);
		SET_RASTER_3x3 = createRaster("3x3","3x3", '3', null,
				"Sets the raster to 3 x 3", null,
				RasterEvent.ActionType.SET_RASTER_3x3);
		SET_RASTER_4x4 = createRaster("4x4", "4x4", '4', null,
				"Sets the raster to 4 x 4", null,
				RasterEvent.ActionType.SET_RASTER_4x4);
		SET_RASTER_5x5 = createRaster("5x5", "5x5", '5', null,
				"Sets the raster to 5 x 5", null,
				RasterEvent.ActionType.SET_RASTER_5x5);
		TOGGLE_TABULAR_VIEW = createRaster("raster", "Toggle raster", 'T', (int) '1',
				"Toggle tabular viewing", null,
				RasterEvent.ActionType.TOGGLE_RASTER_VIEW);
		all = Arrays.asList(TOGGLE_TABULAR_VIEW,
				ADD_COLUMN, ADD_ROW, REMOVE_COLUMN, REMOVE_ROW,
				SET_DEFAULT_RASTER, SET_RASTER_2x2, SET_RASTER_3x3,
				SET_RASTER_4x4, SET_RASTER_5x5);
	
		
	}


	public UserAction createRaster(String id, String caption, 
			int key, Integer altKey, String description, ImageResource icon, RasterEvent.ActionType actionType) {
		return create(id, caption, key, altKey, description, icon, raster.get(actionType));
	}

	@Override
	public List<UserAction> allActions() {
		return all;
	}


	@Override
	public UserAction toggleTabularView() {
		return TOGGLE_TABULAR_VIEW;
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

}
