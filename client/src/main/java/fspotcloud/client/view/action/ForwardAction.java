package fspotcloud.client.view.action;

import com.google.gwt.event.shared.EventBus;
import com.google.inject.Inject;

import fspotcloud.client.main.gin.ActivePagerPresenter;
import fspotcloud.client.main.shared.ForwardGestureEvent;
import fspotcloud.client.view.PagerView.PagerPresenter;

public class ForwardAction implements GestureAction {

	final EventBus eventBus;
	
	@Inject
	public ForwardAction(EventBus eventBus) {
		this.eventBus = eventBus;
	}
	
	@Override
	public void perform() {
		eventBus.fireEvent(new ForwardGestureEvent());
	}

}
