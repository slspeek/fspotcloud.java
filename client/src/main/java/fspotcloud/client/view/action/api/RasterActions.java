package fspotcloud.client.view.action.api;

public interface RasterActions extends ActionGroup {
	UserAction addColumm();
	UserAction addRow();
	UserAction removeColumn();
	UserAction removeRow();
	UserAction resetRaster();
	UserAction setRaster2x2();
	UserAction setRaster3x3();
	UserAction setRaster4x4();
	UserAction setRaster5x5();
	UserAction toggleTabularView();
}
