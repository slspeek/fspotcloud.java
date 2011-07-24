package fspotcloud.client.main.ui;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Widget;

import fspotcloud.client.main.view.api.ButtonPanelView;

public class ButtonPanelViewImpl extends DockLayoutPanel implements ButtonPanelView {

	HorizontalPanel northPanel = new HorizontalPanel();
	HorizontalPanel southPanel = new HorizontalPanel();
	
	public ButtonPanelViewImpl() {
		super(Unit.PX);
		super.addNorth(northPanel, 48);
		super.addSouth(southPanel, 48);
	}
	
	public void addNorth(Widget w) {
		northPanel.add(w);
	}
	
	public void addSouth(Widget w) {
		southPanel.add(w);
	}
}
