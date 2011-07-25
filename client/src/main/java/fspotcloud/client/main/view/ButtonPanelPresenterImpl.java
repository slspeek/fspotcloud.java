package fspotcloud.client.main.view;

import java.util.logging.Logger;

import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

import fspotcloud.client.main.view.api.ButtonPanelView;
import fspotcloud.client.main.view.api.SlideshowPresenterFactory;
import fspotcloud.client.main.view.api.SlideshowView;
import fspotcloud.client.main.view.api.UserButtonFactory;
import fspotcloud.client.main.view.api.UserButtonView;
import fspotcloud.client.view.action.api.ActionGroup;
import fspotcloud.client.view.action.api.AllUserActions;
import fspotcloud.client.view.action.api.SlideshowActions;
import fspotcloud.client.view.action.api.UserAction;

public class ButtonPanelPresenterImpl implements
		ButtonPanelView.ButtonPanelPresenter {
	@SuppressWarnings("unused")
	private static final Logger log = Logger
			.getLogger(ButtonPanelPresenterImpl.class.getName());
	private final ButtonPanelView buttonPanelView;
	private final AllUserActions allActions;
	private final UserButtonFactory buttonPresenterFactory;
	final private SlideshowView.SlideshowPresenter slideshowPresenter;

	@Inject
	public ButtonPanelPresenterImpl(@Assisted ButtonPanelView buttonPanelView,
			AllUserActions allActions,
			UserButtonFactory buttonPresenterFactory, SlideshowPresenterFactory slideshowPresenterFactory) {
		super();
		this.buttonPanelView = buttonPanelView;
		this.allActions = allActions;
		this.buttonPresenterFactory = buttonPresenterFactory;
		this.slideshowPresenter = slideshowPresenterFactory.get(buttonPanelView.getSlideshowView());
	}

	@Override
	public void init() {
		addActionGroup(allActions.navigation(), true);
		
		addSpacer(true);
		addActionGroup(allActions.raster(), true);
		
		SlideshowActions actions = allActions.slideshow();
		addAction(actions.slower(), false);
		addAction(actions.stopSlideshow(),false);
		addSpacer(false);
		Widget w = slideshowPresenter.getView().asWidget();
		buttonPanelView.add(w, false);
		addSpacer(false);
		addAction(actions.startSlideshow(), true);
		addAction(actions.faster(), true);
		addSpacer(false);
		
		addActionGroup(allActions.application(), false);
		slideshowPresenter.init();
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
		buttonPanelView.add(buttonPresenter.getView(), north);
	}

	private void addSpacer(boolean north) {
		Widget space = buttonPanelView.getSpacer();
		buttonPanelView.add(space, north);
	}
}
