package fspotcloud.client.main.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.DecoratedPopupPanel;

public class HelpPopup extends DecoratedPopupPanel {

	private static HelpPopupUiBinder uiBinder = GWT
			.create(HelpPopupUiBinder.class);

	interface HelpPopupUiBinder extends UiBinder<Element, HelpPopup> {
	}

	@UiField
	SpanElement helpBodySpan;

	public HelpPopup(String helpHtml) {
		super(true);
		setElement(uiBinder.createAndBindUi(this));
		helpBodySpan.setInnerHTML(helpHtml);
	}
}
