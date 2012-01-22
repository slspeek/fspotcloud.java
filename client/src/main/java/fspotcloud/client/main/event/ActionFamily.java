package fspotcloud.client.main.event;

import java.util.List;

public interface ActionFamily {
	List<ActionMap> allMaps();

	ActionMap get(String description);

	String getDescription();
}
