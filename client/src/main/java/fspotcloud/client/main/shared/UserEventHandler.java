package fspotcloud.client.main.shared;

import com.google.gwt.event.shared.EventHandler;

public interface UserEventHandler extends EventHandler{

	void onEvent(UserEvent<?> event);
}
