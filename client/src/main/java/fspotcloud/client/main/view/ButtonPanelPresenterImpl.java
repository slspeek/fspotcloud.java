package fspotcloud.client.main.view;

import java.util.logging.Logger;

import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

import fspotcloud.client.main.event.ActionFamily;
import fspotcloud.client.main.event.ActionMap;
import fspotcloud.client.main.event.slideshow.SlideshowType;
import fspotcloud.client.main.view.api.ButtonPanelView;
import fspotcloud.client.main.view.api.SlideshowPresenterFactory;
import fspotcloud.client.main.view.api.SlideshowView;
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
	final private SlideshowView.SlideshowPresenter slideshowPresenter;

	@Inject
	public ButtonPanelPresenterImpl(@Assisted ButtonPanelView buttonPanelView,
			ActionFamily allActions,
			UserButtonPresenterFactory buttonPresenterFactory,
			SlideshowPresenterFactory slideshowPresenterFactory) {
		super();
		this.buttonPanelView = buttonPanelView;
		this.allActions = allActions;
		this.buttonPresenterFactory = buttonPresenterFactory;
		this.slideshowPresenter = slideshowPresenterFactory.get(buttonPanelView
				.getSlideshowView());
	}

	@Override
	public void init() {
		addActionGroup(allActions.get("Navigation"), true);

		addSpacer(true);
		addActionGroup(allActions.get("Raster"), true);

		ActionMap actions = allActions.get("Slideshow");
		addAction(actions.get(SlideshowType.SLIDESHOW_SLOWER), false);
		addAction(actions.get(SlideshowType.SLIDESHOW__END), false);
		addSpacer(false);
		Widget w = slideshowPresenter.getView().asWidget();
		buttonPanelView.add(w, false);
		addSpacer(false);
		addAction(actions.get(SlideshowType.SLIDESHOW_START), false);
		addAction(actions.get(SlideshowType.SLIDESHOW_FASTER), false);
		addSpacer(false);

		addActionGroup(allActions.get("Application"), false);
		slideshowPresenter.init();
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
