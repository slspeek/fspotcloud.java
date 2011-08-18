package fspotcloud.client.main.event;

import java.util.logging.Logger;

import com.google.gwt.event.shared.GwtEvent;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

import fspotcloud.client.main.event.slideshow.SlideshowEvent;
import fspotcloud.client.view.action.api.ActionDef;

public class UserEvent<H extends UserEventHandler> extends GwtEvent<H> {

	private static final Logger log = Logger.getLogger(UserEvent.class
			.getName());
	final public Type<H> TYPE = new Type<H>();
	private ActionDef actionDef;

	@Inject 
	public UserEvent(@Assisted ActionDef actionDef) {
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

	public boolean equals(Object o) {
		if (o instanceof UserEvent) {
			UserEvent other = (UserEvent) o;
			if (getActionDef() == other.getActionDef() ) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	public int hashCode() {
		return getActionDef().toString().hashCode();
	}

}
