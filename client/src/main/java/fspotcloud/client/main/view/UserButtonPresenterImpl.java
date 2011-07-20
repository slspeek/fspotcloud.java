package fspotcloud.client.main.view;

import java.util.logging.Logger;

import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

import fspotcloud.client.main.view.api.UserButtonView;
import fspotcloud.client.view.action.api.UserAction;

public class UserButtonPresenterImpl implements UserButtonView.UserButtonPresenter {
	private static final Logger log = Logger
	.getLogger(UserButtonPresenterImpl.class.getName());

	private final UserAction action;
	private final UserButtonView view;

	@Inject
	public UserButtonPresenterImpl(@Assisted UserAction action, UserButtonView view) {
		super();
		this.action = action;
		this.view = view;
	}
	
	@Override
	public void init() {
		initButton();
		view.setPresenter(this);
	}
	
	private void initButton() {
		view.setCaption(action.getCaption());
	}

	@Override
	public void buttonClicked() {
		action.run();
	}

	@Override
	public Widget getView() {
		return view.asWidget();
	}
	
}
