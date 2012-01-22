package fspotcloud.client.main.view;

import java.util.logging.Logger;

import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

import fspotcloud.client.main.view.api.UserButtonView;
import fspotcloud.client.main.view.api.UserButtonViewFactory;
import fspotcloud.client.view.action.KeyStroke;
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
		String caption = action.getCaption();
		if (action.getIcon() == null) {
			view.setCaption(caption);
		}
		String tooltip = getTooltip();
		view.setTooltip(tooltip);
		view.setDebugId(action.getId());
	}

	private String getTooltip() {
		KeyStroke key = action.getKey();
		KeyStroke altKey = action.getAlternateKey();
		String caption = action.getCaption();
		String tip = caption + " ( " + key.getKeyString() + " )";
		if (altKey != null) {
			tip  =  caption + "( " + key.getKeyString() + " or " + altKey.getKeyString() + " )";
		}
		return tip;
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
