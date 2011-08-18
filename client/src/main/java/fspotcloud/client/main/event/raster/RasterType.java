package fspotcloud.client.main.event.raster;

import fspotcloud.client.view.action.KeyStroke;
import fspotcloud.client.view.action.api.ActionDef;

public enum RasterType implements ActionDef {

	ADD_COLUMN("add_colum", "Add column", "Adds one column to raster",
			new KeyStroke('C'), null), ADD_ROW("add-row", "Add row",
			"Adds one row to raster", new KeyStroke('R'), null), REMOVE_COLUMN(
			"remove-column", "Remove column",
			"Removes one column from the raster", new KeyStroke('X'), null), REMOVE_ROW(
			"remove-row", "Remove row", "Removes one row from the raster",
			new KeyStroke('E'), null), SET_DEFAULT_RASTER("reset",
			"Reset raster", "Resets raster to defaults", new KeyStroke('0'),
			null), SET_RASTER_2x2("2x2", "2x2", "Sets the raster to 2 x 2",
			new KeyStroke('2'), null), SET_RASTER_3x3("3x3", "3x3",
			"Sets the raster to 3 x 3", new KeyStroke('3'), null), SET_RASTER_4x4(
			"4x4", "4x4", "Sets the raster to 4 x 4", new KeyStroke('4'), null), SET_RASTER_5x5(
			"5x5", "5x5", "Sets the raster to 5 x 5", new KeyStroke('5'), null), TOGGLE_TABULAR_VIEW(
			"raster", "Toggle raster", "Toggle tabular viewing", new KeyStroke(
					'T'), new KeyStroke((int) '1'));

	final private KeyStroke key;
	final private KeyStroke alternateKey;
	final private String caption;
	final private String id;
	final private String description;

	private RasterType(String id, String caption, String description,
			KeyStroke key, KeyStroke alternateKey) {
		this.key = key;
		this.alternateKey = alternateKey;
		this.caption = caption;
		this.id = id;
		this.description = description;
	}

	@Override
	public KeyStroke getAlternateKey() {
		return alternateKey;
	}

	@Override
	public KeyStroke getKey() {
		return key;
	}

	@Override
	public String getCaption() {
		return caption;
	}

	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public String getId() {
		return id;
	}

}
