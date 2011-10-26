package fspotcloud.client.main.view.api;

import java.util.logging.Logger;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.name.Named;

import fspotcloud.client.main.event.ActionFamily;
import fspotcloud.client.main.event.ActionMap;
import fspotcloud.client.main.event.slideshow.SlideshowType;
import fspotcloud.client.main.view.ButtonPanelPresenterImpl;
import fspotcloud.client.main.view.api.ButtonPanelView.ButtonPanelPresenter;
import fspotcloud.client.view.action.api.UserAction;

public class ButtonPanelPresenterProvider implements Provider<ButtonPanelView.ButtonPanelPresenter>{

	@SuppressWarnings("unused")
	private static final Logger log = Logger
			.getLogger(ButtonPanelPresenterProvider.class.getName());
	private final ButtonPanelView buttonPanelView;
	private final ActionFamily allActions;
	private final UserButtonPresenterFactory buttonPresenterFactory;

	@Inject
	public ButtonPanelPresenterProvider(@Named("Main") ButtonPanelView buttonPanelView,
			ActionFamily allActions,
			UserButtonPresenterFactory buttonPresenterFactory) {
		super();
		this.buttonPanelView = buttonPanelView;
		this.allActions = allActions;
		this.buttonPresenterFactory = buttonPresenterFactory;
		init();
	}

	
	public void init() {
		int total = 0;
		ActionMap nav = allActions.get("Navigation");
		total += nav.allActions().size();
		
		ActionMap raster = allActions.get("Raster"); 
		total += raster.allActions().size();
		total++; //Slideshow
		ActionMap app  = allActions.get("Application");
		total += app.allActions().size();
		buttonPanelView.setWidgetCount(total);
		
		
		addActionGroup(nav);
		addActionGroup(raster);
		ActionMap actions = allActions.get("Slideshow");
		addAction(actions.get(SlideshowType.SLIDESHOW_START));
		addActionGroup(app);
	}

	private void addActionGroup(ActionMap group) {
		for (UserAction action : group.allActions()) {
			addAction(action);
		}
	}

	private void addAction(UserAction action) {
		UserButtonView.UserButtonPresenter buttonPresenter = buttonPresenterFactory
				.get(action);
		buttonPresenter.init();
		buttonPanelView.add(buttonPresenter.getView());
	}

	@Override
	public ButtonPanelPresenter get() {
		return new ButtonPanelPresenterImpl(buttonPanelView);
	}

}
