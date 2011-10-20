package fspotcloud.client.main.view.api;

import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.Widget;

import fspotcloud.client.main.api.Initializable;

public interface ButtonPanelView extends IsWidget {
	interface ButtonPanelPresenter extends Initializable {
	}

	void setButtonCount(int count);
	
	void add(Widget widget);

	SlideshowView getSlideshowView();
	
	LayoutPanel getLayout();
}
