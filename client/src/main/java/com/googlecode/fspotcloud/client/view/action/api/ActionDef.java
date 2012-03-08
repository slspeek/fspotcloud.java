package com.googlecode.fspotcloud.client.view.action.api;

import com.googlecode.fspotcloud.client.view.action.KeyStroke;

public interface ActionDef  {
	KeyStroke getAlternateKey();

	KeyStroke getKey();
	
	String getCaption();

	String getDescription();

	String getId();
}
