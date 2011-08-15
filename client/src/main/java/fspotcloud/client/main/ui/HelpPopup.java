package fspotcloud.client.main.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.inject.Inject;

public class HelpPopup extends PopupPanel {

	private static HelpPopupUiBinder uiBinder = GWT
			.create(HelpPopupUiBinder.class);

	interface HelpPopupUiBinder extends UiBinder<HTMLPanel, HelpPopup> {
	}

	@UiField
	DivElement helpBodyDiv;

	@Inject
	public HelpPopup(Resources resources) {
		super(true);
		setWidget(uiBinder.createAndBindUi(this));
		setStyleName(resources.style().helpPopup());
	}
	
	public void setText(String text) {
		helpBodyDiv.setInnerHTML(text);
	}
}
