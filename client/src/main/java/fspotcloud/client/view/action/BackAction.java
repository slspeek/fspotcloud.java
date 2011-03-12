package fspotcloud.client.view.action;

import com.google.gwt.event.shared.EventBus;
import com.google.inject.Inject;

import fspotcloud.client.main.shared.BackGestureEvent;

public class BackAction implements GestureAction {

	final EventBus eventBus;
	@Inject
	public BackAction(EventBus eventBus) {
		this.eventBus = eventBus;
	}
	@Override
	public void perform() {
		eventBus.fireEvent(new BackGestureEvent());
	}

}
