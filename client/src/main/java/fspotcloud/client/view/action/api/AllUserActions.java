package fspotcloud.client.view.action.api;

import java.util.List;


public interface AllUserActions {
	UserAction back();
	UserAction next();
	UserAction toggleTabularView();
	UserAction toggleTreeVisible();
	UserAction treeFocus();
	UserAction addColumm();
	UserAction addRow();
	UserAction removeColumn();
	UserAction removeRow();
	UserAction resetRaster();
	UserAction setRaster2x2();
	UserAction setRaster3x3();
	UserAction setRaster4x4();
	UserAction setRaster5x5();
	UserAction home();
	UserAction end();
	UserAction startSlideshow();
	UserAction stopSlideshow();
	UserAction slower();
	UserAction faster();
	UserAction toggleHelp();
	UserAction demo();
	List<UserAction> allActions();
}
