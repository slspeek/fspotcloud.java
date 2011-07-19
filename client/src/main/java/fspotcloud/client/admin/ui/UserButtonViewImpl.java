package fspotcloud.client.admin.ui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.PushButton;

import fspotcloud.client.main.view.api.UserButtonView;

public class UserButtonViewImpl extends PushButton implements UserButtonView {

	private UserButtonPresenter presenter;

	public UserButtonViewImpl() {
	}

	
	@Override
	public void setPresenter(UserButtonPresenter presenter) {
		this.presenter = presenter;
		registerClickEvents();
	}

	private void registerClickEvents() {
		//Maybe unregister old one first?
		//For now setPresenter must be called only once.
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
}
