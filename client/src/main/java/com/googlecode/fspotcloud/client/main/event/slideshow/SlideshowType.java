package com.googlecode.fspotcloud.client.main.event.slideshow;

import com.google.gwt.event.dom.client.KeyCodes;

import com.googlecode.fspotcloud.client.view.action.KeyStroke;
import com.googlecode.fspotcloud.client.view.action.api.ActionDef;

public enum SlideshowType implements ActionDef {
	
	SLIDESHOW_START("play", "Play",	"Start slideshow", new KeyStroke('S'), new KeyStroke('G')),
	SLIDESHOW__END("stop", "Stop", "Stop slideshow", new KeyStroke('Q'), new KeyStroke(KeyCodes.KEY_ESCAPE)),
	SLIDESHOW_PAUSE("pause", "Pause", "Pause slideshow", new KeyStroke(32), new KeyStroke(19)),
	SLIDESHOW_SLOWER ("slower", "Slower","Makes the slideshow go slower", new KeyStroke('U'), null),
	SLIDESHOW_FASTER ("faster", "Faster", "Makes the slideshow go faster", new KeyStroke('I'), null);
	
	final private KeyStroke key;
	final private KeyStroke alternateKey;
	final private String caption;
	final private String id;
	final private String description;

	private SlideshowType(String id, String caption,
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
