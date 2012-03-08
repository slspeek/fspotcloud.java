package com.googlecode.fspotcloud.client.main.event.raster;

import com.google.gwt.resources.client.ImageResource;
import com.google.inject.Inject;

import com.googlecode.fspotcloud.client.main.event.AbstractActionMap;
import com.googlecode.fspotcloud.client.main.ui.Resources;
import com.googlecode.fspotcloud.client.view.action.api.ActionDef;
import com.googlecode.fspotcloud.client.view.action.api.UserActionFactory;

public class RasterMapBuilder extends AbstractActionMap {

	private Resources resources;

	@Inject
	public RasterMapBuilder(UserActionFactory userActionFactory,
			Resources resources) {
		super(userActionFactory, "Raster");
		this.resources = resources;
	}

	@Override
	public void buildMap() {
		put(RasterType.ADD_COLUMN, resources.addColumnIcon());
		put(RasterType.ADD_ROW, resources.addRowIcon());
		put(RasterType.REMOVE_COLUMN, resources.removeColumnIcon());
		put(RasterType.REMOVE_ROW, resources.removeRowIcon());
		put(RasterType.SET_DEFAULT_RASTER, resources.resetIcon());
		put(RasterType.SET_RASTER_2x2, resources.icon2x2());
		put(RasterType.SET_RASTER_3x3, resources.icon3x3());
		put(RasterType.SET_RASTER_4x4, resources.icon4x4());
		put(RasterType.SET_RASTER_5x5, resources.icon5x5());
		put(RasterType.TOGGLE_TABULAR_VIEW, resources.tabularIcon());
	}

	private void put(ActionDef actionDef, ImageResource icon) {
		put(actionDef, icon, new RasterEventProvider(actionDef));
	}
}
