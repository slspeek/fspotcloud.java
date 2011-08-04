package fspotcloud.client.main.view;

import java.util.logging.Logger;

import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

import fspotcloud.client.main.view.api.UserButtonView;
import fspotcloud.client.main.view.api.UserButtonViewFactory;
import fspotcloud.client.view.action.api.UserAction;

public class UserButtonPresenterImpl implements
		UserButtonView.UserButtonPresenter {
	private static final Logger log = Logger
			.getLogger(UserButtonPresenterImpl.class.getName());

	private final UserAction action;
	private final UserButtonViewFactory viewFactory;
	private UserButtonView view;

	@Inject
	public UserButtonPresenterImpl(@Assisted UserAction action,
			UserButtonViewFactory viewFactory) {
		super();
		this.action = action;
		this.viewFactory = viewFactory;
	}

	@Override
	public void init() {
		view = viewFactory.get(action);
		view.setPresenter(this);
		initButton();
	}

	private void initButton() {
		if (action.getIcon() == null) {
			view.setCaption(action.getCaption());
		}
		view.setDebugId(action.getId());
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
