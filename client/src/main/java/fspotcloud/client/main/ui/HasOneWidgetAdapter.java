package fspotcloud.client.main.ui;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.HasOneWidget;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;

public class HasOneWidgetAdapter implements HasOneWidget {

	final private DockLayoutPanel panel;
	public HasOneWidgetAdapter(DockLayoutPanel panel) {
		this.panel = panel;
	}

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
			panel.remove(currentWidget);
		}
		
	}
	@Override
	public Widget getWidget() {
		return currentWidget;
	}

	@Override
	public void setWidget(Widget w) {
		clear();
		currentWidget = w;
		if (w != null) {
			panel.add(w);
		}
	}

}
