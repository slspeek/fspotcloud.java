package fspotcloud.client.view.action;

public class Shortcut {
	final private String description;
	final private KeyStroke key;
	final private KeyStroke alternateKey;
	
	public Shortcut(String description, KeyStroke key, KeyStroke alternateKey) {
		super();
		this.description = description;
		this.key = key;
		this.alternateKey = alternateKey;
	}

	public KeyStroke getAlternateKey() {
		return alternateKey;
	}

	public KeyStroke getKey() {
		return key;
	}

	public String getDescription() {
		return description;
	}
	
}
