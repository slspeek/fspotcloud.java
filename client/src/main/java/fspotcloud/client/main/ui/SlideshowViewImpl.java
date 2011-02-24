package fspotcloud.client.main.ui;

import java.util.logging.Logger;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.Widget;

import fspotcloud.client.view.SlideshowView;

public class SlideshowViewImpl extends Composite implements SlideshowView {
	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(SlideshowViewImpl.class
			.getName());
	private static SlideshowViewImplUiBinder uiBinder = GWT
			.create(SlideshowViewImplUiBinder.class);

	interface SlideshowViewImplUiBinder extends
			UiBinder<Widget, SlideshowViewImpl> {
	}

	@UiField
	HTMLPanel mainPanel;
	@UiField
	PushButton slowerButton;
	@UiField
	PushButton startButton;
	@UiField
	Label intervalLabel;
	@UiField
	PushButton stopButton;
	@UiField
	PushButton fasterButton;

	public SlideshowViewImpl() {
		initWidget(uiBinder.createAndBindUi(this));
		log.info("Created");
		mainPanel.addStyleName("fsc-slideshow");
		slowerButton.addStyleName("fsc-slideshow-increase");
		startButton.addStyleName("fsc-slideshow-start");
		stopButton.addStyleName("fsc-slideshow-stop");
		fasterButton.addStyleName("fsc-slideshow-decrease");
	}

	@UiHandler("slowerButton")
	public void onNextButtonClicked(ClickEvent event) {
		// presenter.slower();
	}

	@Override
	public void setLabelText(String text) {
		intervalLabel.setText(text);
	}

	@Override
	public void setSlideshowPresenter(SlideshowPresenter presenter) {
		// TODO Auto-generated method stub

	}

}
