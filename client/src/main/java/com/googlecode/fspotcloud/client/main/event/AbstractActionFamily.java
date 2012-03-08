package com.googlecode.fspotcloud.client.main.event;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public abstract class AbstractActionFamily implements ActionFamily {

	Map<String,ActionMap> familyMap = new HashMap<String,ActionMap>();
	final private String description;
	
	public AbstractActionFamily(String description) {
		this.description = description;
	}
	@Override
	public List<ActionMap> allMaps() {
		return new ArrayList<ActionMap>(familyMap.values());
	}

	@Override
	public ActionMap get(String name) {
		return familyMap.get(name);
	}

	@Override
	public String getDescription() {
		return description;
	}
	
	public void addMap(AbstractActionMap actionMap) {
		actionMap.buildMap();
		familyMap.put(actionMap.getDescription(), actionMap);
	}
	
}
