package fspotcloud.client.view.action;

import com.google.gwt.event.shared.EventBus;
import com.google.inject.Inject;

import fspotcloud.client.main.shared.ForwardGestureEvent;

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
