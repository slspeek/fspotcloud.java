package com.googlecode.fspotcloud.client.main.ui;

import java.util.logging.Logger;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

import com.googlecode.fspotcloud.client.main.view.api.SlideshowView;

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
	HorizontalPanel mainPanel;

	@UiField
	Label intervalLabel;

	public SlideshowViewImpl() {
		initWidget(uiBinder.createAndBindUi(this));
		log.info("created");
	}

	@Override
	public void setLabelText(String text) {
		intervalLabel.setText(text);
	}
}
