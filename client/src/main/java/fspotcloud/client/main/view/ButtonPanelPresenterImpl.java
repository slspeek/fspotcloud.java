package fspotcloud.client.main.view;

import java.util.logging.Logger;

import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.inject.name.Named;

import fspotcloud.client.main.event.ActionFamily;
import fspotcloud.client.main.event.ActionMap;
import fspotcloud.client.main.event.slideshow.SlideshowType;
import fspotcloud.client.main.view.api.ButtonPanelView;
import fspotcloud.client.main.view.api.UserButtonPresenterFactory;
import fspotcloud.client.main.view.api.UserButtonView;
import fspotcloud.client.view.action.api.UserAction;

public class ButtonPanelPresenterImpl implements
		ButtonPanelView.ButtonPanelPresenter {
	@SuppressWarnings("unused")
	private static final Logger log = Logger
			.getLogger(ButtonPanelPresenterImpl.class.getName());
	private final ButtonPanelView buttonPanelView;
	private final ActionFamily allActions;
	private final UserButtonPresenterFactory buttonPresenterFactory;

	@Inject
	public ButtonPanelPresenterImpl(@Named("Main") ButtonPanelView buttonPanelView,
			ActionFamily allActions,
			UserButtonPresenterFactory buttonPresenterFactory) {
		super();
		this.buttonPanelView = buttonPanelView;
		this.allActions = allActions;
		this.buttonPresenterFactory = buttonPresenterFactory;
	}

	@Override
	public void init() {
		addActionGroup(allActions.get("Navigation"), true);

		addSpacer(true);
		addActionGroup(allActions.get("Raster"), true);

		ActionMap actions = allActions.get("Slideshow");

		addAction(actions.get(SlideshowType.SLIDESHOW_START), false);
		addSpacer(false);

		addActionGroup(allActions.get("Application"), false);
	}

	private void addActionGroup(ActionMap group, boolean north) {
		for (UserAction action : group.allActions()) {
			addAction(action, north);
		}
	}

	private void addAction(UserAction action, boolean north) {
		UserButtonView.UserButtonPresenter buttonPresenter = buttonPresenterFactory
				.get(action);
		buttonPresenter.init();
		buttonPanelView.add(buttonPresenter.getView(), north);
	}

	private void addSpacer(boolean north) {
		Widget space = buttonPanelView.getSpacer();
		buttonPanelView.add(space, north);
	}
}
