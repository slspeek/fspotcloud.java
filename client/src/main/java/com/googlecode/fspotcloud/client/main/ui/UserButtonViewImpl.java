package com.googlecode.fspotcloud.client.main.ui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.PushButton;
import com.google.inject.Inject;

import com.googlecode.fspotcloud.client.main.view.api.UserButtonView;

public class UserButtonViewImpl extends PushButton implements UserButtonView {

	private UserButtonPresenter presenter;
	final private Resources resources;

	@Inject
	public UserButtonViewImpl(Resources resources, ImageResource icon) {
		super(new Image(icon));
		this.resources = resources;
		setStyleName(resources.style().button());
	}

	@Inject 
	public UserButtonViewImpl(Resources resources) {
		this.resources = resources;
		setStyleName(resources.style().button());
	}

	@Override
	public void setPresenter(UserButtonPresenter presenter) {
		this.presenter = presenter;
		registerClickEvents();
	}

	private void registerClickEvents() {
		// Maybe unregister old one first?
		// For now setPresenter must be called only once.
		addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				if (presenter != null) {
					presenter.buttonClicked();
				}
			}
		});
	}

	@Override
	public void setCaption(String caption) {
		setText(caption);

	}

	@Override
	public void setTooltip(String tooltip) {
		asWidget().setTitle(tooltip);
	}

	@Override
	public void setDebugId(String id) {
		ensureDebugId(id);
	}
}
