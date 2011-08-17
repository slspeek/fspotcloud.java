package fspotcloud.client.view.action.api;

import fspotcloud.client.view.action.KeyStroke;

public interface ActionDef  {
	KeyStroke getAlternateKey();

	KeyStroke getKey();
	
	String getCaption();

	String getDescription();

	String getId();
}
