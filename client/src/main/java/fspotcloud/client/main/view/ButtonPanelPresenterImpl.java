package fspotcloud.client.main.view;

import java.util.logging.Logger;

import com.google.gwt.user.client.DOM;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

import fspotcloud.client.main.view.api.ButtonPanelView;
import fspotcloud.client.main.view.api.UserButtonFactory;
import fspotcloud.client.main.view.api.UserButtonView;
import fspotcloud.client.view.action.api.ActionGroup;
import fspotcloud.client.view.action.api.AllUserActions;
import fspotcloud.client.view.action.api.UserAction;

public class ButtonPanelPresenterImpl implements
		ButtonPanelView.ButtonPanelPresenter {
	private static final Logger log = Logger
			.getLogger(ButtonPanelPresenterImpl.class.getName());
	private final ButtonPanelView buttonPanelView;
	private final AllUserActions allActions;
	private final UserButtonFactory buttonPresenterFactory;

	@Inject
	public ButtonPanelPresenterImpl(@Assisted ButtonPanelView buttonPanelView,
			AllUserActions allActions, UserButtonFactory buttonPresenterFactory) {
		super();
		this.buttonPanelView = buttonPanelView;
		this.allActions = allActions;
		this.buttonPresenterFactory = buttonPresenterFactory;
	}

	@Override
	public void init() {
		// log.info("init called!!");
		addActionGroup(allActions.raster(), true);
		addActionGroup(allActions.navigation(), true);
		addActionGroup(allActions.slideshow(), false);
		addActionGroup(allActions.application(), false);
	}

	private void addActionGroup(ActionGroup group, boolean north) {
		for (UserAction action : group.allActions()) {
			addAction(action, north);
		}
	}

	private void addAction(UserAction action, boolean north) {
		UserButtonView.UserButtonPresenter buttonPresenter = buttonPresenterFactory
				.get(action);
		buttonPresenter.init();
		if (north) {
			buttonPanelView.addNorth(buttonPresenter.getView());
		} else {
			buttonPanelView.addSouth(buttonPresenter.getView());
		}
	}
}
