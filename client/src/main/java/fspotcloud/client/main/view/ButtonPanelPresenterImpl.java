package fspotcloud.client.main.view;

import fspotcloud.client.main.view.api.ButtonPanelView;

public class ButtonPanelPresenterImpl implements
		ButtonPanelView.ButtonPanelPresenter {

	@SuppressWarnings("unused")
	private final ButtonPanelView view;

	
	public ButtonPanelPresenterImpl(ButtonPanelView view) {
		super();
		this.view = view;
	}
	
}
