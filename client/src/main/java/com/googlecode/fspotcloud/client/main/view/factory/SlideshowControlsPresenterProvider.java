package com.googlecode.fspotcloud.client.main.view.factory;

import java.util.logging.Logger;

import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.name.Named;

import com.googlecode.fspotcloud.client.main.event.AbstractActionMap;
import com.googlecode.fspotcloud.client.main.event.slideshow.SlideshowType;
import com.googlecode.fspotcloud.client.main.view.SlideshowControlsPresenter;
import com.googlecode.fspotcloud.client.main.view.api.ButtonPanelView;
import com.googlecode.fspotcloud.client.main.view.api.SlideshowView;
import com.googlecode.fspotcloud.client.main.view.api.UserButtonPresenterFactory;
import com.googlecode.fspotcloud.client.main.view.api.UserButtonView;
import com.googlecode.fspotcloud.client.view.action.api.UserAction;

public class SlideshowControlsPresenterProvider implements Provider<SlideshowControlsPresenter>{
	private static final Logger log = Logger
			.getLogger(SlideshowControlsPresenterProvider.class.getName());

	final private AbstractActionMap actions;
	final private UserButtonPresenterFactory buttonPresenterFactory;
	final private SlideshowView slideshowView;
	final private SlideshowView.SlideshowPresenter slideshowPresenter;
	final private ButtonPanelView buttonPanelView;

	@Inject
	public SlideshowControlsPresenterProvider(
			@Named("Slideshow") ButtonPanelView buttonPanelView,
			@Named("slideshow") AbstractActionMap actions,
			UserButtonPresenterFactory buttonPresenterFactory,
			SlideshowView slideshowView, SlideshowView.SlideshowPresenter slideshowPresenter) {
		super();
		this.buttonPanelView = buttonPanelView;
		this.slideshowPresenter = slideshowPresenter;
		this.actions = actions;
		actions.buildMap();
		this.buttonPresenterFactory = buttonPresenterFactory;
		this.slideshowView = slideshowView;
		log.info("created");
		init();
	}

	
	public void init() {
		buttonPanelView.setWidgetCount(actions.allActions().size() + 1);
		addAction(actions.get(SlideshowType.SLIDESHOW_SLOWER));
		addAction(actions.get(SlideshowType.SLIDESHOW_START));
		addAction(actions.get(SlideshowType.SLIDESHOW_PAUSE));
	
		Widget w = slideshowView.asWidget();
		buttonPanelView.add(w);
		
		addAction(actions.get(SlideshowType.SLIDESHOW__END));
		addAction(actions.get(SlideshowType.SLIDESHOW_FASTER));
	}

	private void addAction(UserAction action) {
		UserButtonView.UserButtonPresenter buttonPresenter = buttonPresenterFactory
				.get(action);
		buttonPresenter.init();
		buttonPanelView.add(buttonPresenter.getView());
	}

	@Override
	public SlideshowControlsPresenter get() {
		return new SlideshowControlsPresenter(slideshowPresenter, buttonPanelView);
	}
	
}
