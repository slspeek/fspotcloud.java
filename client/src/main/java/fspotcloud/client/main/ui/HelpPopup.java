package fspotcloud.client.main.ui;

import java.util.logging.Logger;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.inject.Inject;

import fspotcloud.client.main.view.api.PopupView;

public class HelpPopup extends PopupPanel implements PopupView {
	private static final Logger log = Logger
	.getLogger(HelpPopup.class.getName());
	private static HelpPopupUiBinder uiBinder = GWT
			.create(HelpPopupUiBinder.class);

	interface HelpPopupUiBinder extends UiBinder<FocusPanel, HelpPopup> {
	}

	@UiField 
	FocusPanel focusPanel;
	
	@UiField
	DivElement helpBodyDiv;

	@UiField
	DivElement titleSpan;

	@Inject
	public HelpPopup(Resources resources) {
		super(true);
		setWidget(uiBinder.createAndBindUi(this));
		setStyleName(resources.style().helpPopup());
		focusPanel.addKeyDownHandler(new KeyDownHandler() {
			
			@Override
			public void onKeyDown(KeyDownEvent event) {
				log.info("Keydown in help-popup " + event);
				hide();
				
			}
		});
	}
	
	public void setText(String text) {
		helpBodyDiv.setInnerHTML(text);
	}
	
	public void setTitle(String text) {
		titleSpan.setInnerHTML(text);
	}

	@Override
	public void focus() {
		focusPanel.setFocus(true);
	}
}
