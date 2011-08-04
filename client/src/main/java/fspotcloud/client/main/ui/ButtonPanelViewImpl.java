package fspotcloud.client.main.ui;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

import fspotcloud.client.main.view.api.ButtonPanelView;
import fspotcloud.client.main.view.api.SlideshowView;

public class ButtonPanelViewImpl extends DockLayoutPanel implements
		ButtonPanelView {
	private final SlideshowView slideshowView;
	
	HorizontalPanel northPanel = new HorizontalPanel();
	HorizontalPanel southPanel = new HorizontalPanel();

	@Inject 
	public ButtonPanelViewImpl(SlideshowView slideshowView) {
		super(Unit.PX);
		this.slideshowView = slideshowView;
		super.addNorth(northPanel, 48);
		super.addSouth(southPanel, 48);
	}

	@Override
	public void add(Widget w, boolean north) {
		if (north) {
			northPanel.add(w);
		} else {
			southPanel.add(w);
		}
	}

	@Override
	public Widget getSpacer() {
		SimplePanel panel = new SimplePanel();
		panel.setStyleName("spacer");
		return panel.asWidget();
	}

	@Override
	public SlideshowView getSlideshowView() {
		return slideshowView;
	}

}
