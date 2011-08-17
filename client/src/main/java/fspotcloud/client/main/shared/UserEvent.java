package fspotcloud.client.main.shared;

import java.util.logging.Logger;

import com.google.web.bindery.event.shared.Event;

import fspotcloud.client.view.action.api.ActionDef;

public class UserEvent<H extends UserEventHandler> extends Event<H> {

	private static final Logger log = Logger.getLogger(UserEvent.class
			.getName());
	final public Type<H> TYPE = new Type<H>();
	private ActionDef actionDef;

	public UserEvent(ActionDef actionDef) {
		this.actionDef = actionDef;
	}
	
	public ActionDef getActionDef() {
		return actionDef;
	}
	@Override
	public Type<H> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(H handler) {
		log.info("in dispatch");
		handler.onEvent(this);
	}

}
