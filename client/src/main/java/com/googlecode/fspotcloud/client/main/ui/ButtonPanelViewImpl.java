package com.googlecode.fspotcloud.client.main.ui;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

import com.googlecode.fspotcloud.client.main.view.api.ButtonPanelView;

public class ButtonPanelViewImpl extends LayoutPanel implements
		ButtonPanelView {
	
	
	private int widgetCount;
	@Inject 
	public ButtonPanelViewImpl(Resources resources) {
		setStyleName(resources.style().buttonPanelBlock());
	}

	private int currentWidget = 0;
	
	@Override
	public void add(Widget w) {
		float step = 100f/widgetCount;
		super.add(w);
		setWidgetLeftWidth(w, currentWidget * step, Unit.PCT, step, Unit.PCT);
		//forceLayout();
		//last
		currentWidget++;
	}


	@Override
	public void setWidgetCount(int count) {
		this.widgetCount = count;
	}


}
