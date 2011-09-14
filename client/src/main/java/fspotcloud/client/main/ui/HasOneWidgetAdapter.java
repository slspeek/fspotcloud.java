package fspotcloud.client.main.ui;

import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.HasOneWidget;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;

public class HasOneWidgetAdapter implements HasOneWidget {

	final private ComplexPanel panel;
	public HasOneWidgetAdapter(ComplexPanel panel) {
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
			currentWidget = null;
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
