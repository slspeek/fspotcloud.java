package fspotcloud.client.main.view.api;

import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;

public interface ButtonPanelView extends IsWidget{
	interface ButtonPanelPresenter {
		void init();
	}
	
	void add(Widget widget);
}
