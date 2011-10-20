package fspotcloud.client.main.view;

import java.util.logging.Logger;

import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.inject.name.Named;

import fspotcloud.client.main.event.AbstractActionMap;
import fspotcloud.client.main.event.ActionMap;
import fspotcloud.client.main.event.slideshow.SlideshowType;
import fspotcloud.client.main.view.api.ButtonPanelView;
import fspotcloud.client.main.view.api.SlideshowPresenterFactory;
import fspotcloud.client.main.view.api.SlideshowView;
import fspotcloud.client.main.view.api.UserButtonPresenterFactory;
import fspotcloud.client.main.view.api.UserButtonView;
import fspotcloud.client.view.action.api.UserAction;

public class SlideshowControlsPresenter implements ButtonPanelView.ButtonPanelPresenter  {
	
	private static final Logger log = Logger
			.getLogger(SlideshowControlsPresenter.class.getName());

	final private AbstractActionMap actions;
	final private UserButtonPresenterFactory buttonPresenterFactory;
	final private SlideshowView.SlideshowPresenter slideshowPresenter;
	final private ButtonPanelView buttonPanelView;
	
	@Inject
	public SlideshowControlsPresenter(@Named("Slideshow") ButtonPanelView buttonPanelView,
			@Named("slideshow") AbstractActionMap actions,
			UserButtonPresenterFactory buttonPresenterFactory,
			SlideshowPresenterFactory slideshowPresenterFactory) {
		super();
		this.buttonPanelView = buttonPanelView;
		this.actions = actions;
		actions.buildMap();
		this.buttonPresenterFactory = buttonPresenterFactory;
		this.slideshowPresenter = slideshowPresenterFactory.get(buttonPanelView
				.getSlideshowView());
		log.info("created");
	}

	@Override
	public void init() {
		slideshowPresenter.init();
		buttonPanelView.setButtonCount(actions.allActions().size() + 1);
	
		addAction(actions.get(SlideshowType.SLIDESHOW_SLOWER));
		addAction(actions.get(SlideshowType.SLIDESHOW__END));
		
		Widget w = slideshowPresenter.getView().asWidget();
		buttonPanelView.add(w);
		
		addAction(actions.get(SlideshowType.SLIDESHOW_START));
		addAction(actions.get(SlideshowType.SLIDESHOW_FASTER));
	}

	private void addAction(UserAction action) {
		UserButtonView.UserButtonPresenter buttonPresenter = buttonPresenterFactory
				.get(action);
		buttonPresenter.init();
		buttonPanelView.add(buttonPresenter.getView());
	}

	
}
