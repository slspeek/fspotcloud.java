package fspotcloud.client.main.ui;

import com.google.gwt.user.client.ui.HasOneWidget;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.Widget;

public class AppWidget implements HasOneWidget {

	private Widget currentWidget;

	@Override
	public void setWidget(IsWidget w) {
		if (w == null) {
			clear();
		} else {
			setWidget(w.asWidget());
		}
	}

	private void clear() {
		if (currentWidget != null) {
			RootLayoutPanel.get().remove(currentWidget);
		}
		
	}

	@Override
	public Widget getWidget() {
		if (currentWidget == null) {
			return RootLayoutPanel.get().asWidget();
		}
		return currentWidget;
	}

	@Override
	public void setWidget(Widget w) {
		clear();
		currentWidget = w;
		if (w != null) {
			RootLayoutPanel.get().add(w);
		}
	}

}
