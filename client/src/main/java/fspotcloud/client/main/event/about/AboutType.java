package fspotcloud.client.main.event.about;

import fspotcloud.client.view.action.KeyStroke;
import fspotcloud.client.view.action.api.ActionDef;

public enum AboutType implements ActionDef {
	LICENSE("license", "License", "View the license",new KeyStroke('L'), null),
	PROJECT_HOSTING("project-hosting", "Project site","Go to the site on Google Project Hosting", new KeyStroke('J'), null),
	F_SPOT("f-spot", "F-Spot", "Go to the F-Spot site", new KeyStroke('N'), null),
	MAVEN("maven", "Maven site","Go to the Maven generated site", new KeyStroke('M'), null),
	PROTON("proton", "Proton radio","Go to the Proton site", new KeyStroke('P'), null),
	STEVEN("steven", "Authors website",	"Go to the authors website", new KeyStroke('Z'), null);

	
	final private KeyStroke key;
	final private KeyStroke alternateKey;
	final private String caption;
	final private String id;
	final private String description;

	private AboutType(String id, String caption,
			String description,KeyStroke key, KeyStroke alternateKey) {
		this.key = key;
		this.alternateKey = alternateKey;
		this.caption = caption;
		this.id = id;
		this.description = description;
	}

	@Override
	public KeyStroke getAlternateKey() {
		return alternateKey;
	}

	@Override
	public KeyStroke getKey() {
		return key;
	}

	@Override
	public String getCaption() {
		return caption;
	}

	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public String getId() {
		return id;
	}

}
