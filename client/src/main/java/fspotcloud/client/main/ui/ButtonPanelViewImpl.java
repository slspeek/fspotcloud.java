package fspotcloud.client.main.ui;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

import fspotcloud.client.main.view.api.ButtonPanelView;
import fspotcloud.client.main.view.api.SlideshowView;

public class ButtonPanelViewImpl extends LayoutPanel implements
		ButtonPanelView {
	private final SlideshowView slideshowView;
	
	
	private int buttonCount;
	@Inject 
	public ButtonPanelViewImpl(SlideshowView slideshowView, Resources resources) {
		this.slideshowView = slideshowView;
		setStyleName(resources.style().buttonPanelBlock());
		slideshowView.asWidget().setStyleName(resources.style().footerBlock());
	}

	private int currentButton = 0;
	
	@Override
	public void add(Widget w) {
		float step = 100f/buttonCount;
		super.add(w);
		setWidgetLeftWidth(w, currentButton * step, Unit.PCT, step, Unit.PCT);
		//forceLayout();
		//last
		currentButton++;
	}


	@Override
	public SlideshowView getSlideshowView() {
		return slideshowView;
	}

	@Override
	public void setButtonCount(int count) {
		this.buttonCount = count;
	}
	
	public LayoutPanel getLayout() {
		return this;
	}

}
