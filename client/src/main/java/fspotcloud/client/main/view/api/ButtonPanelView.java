package fspotcloud.client.main.view.api;

import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;

import fspotcloud.client.main.api.Initializable;

public interface ButtonPanelView extends IsWidget {
	interface ButtonPanelPresenter extends Initializable {
	}

	void add(Widget widget, boolean north);

	Widget getSpacer();

	SlideshowView getSlideshowView();
}
