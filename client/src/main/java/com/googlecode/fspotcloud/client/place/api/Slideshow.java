package com.googlecode.fspotcloud.client.place.api;

public interface Slideshow {
	
	void start();
	
	void stop();
	
	float faster();
	
	float slower();
	
	float delay();
	
	boolean isRunning();

	void pause();

}
