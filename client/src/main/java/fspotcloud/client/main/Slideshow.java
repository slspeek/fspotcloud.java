package fspotcloud.client.main;

public interface Slideshow {
	
	void start();
	
	void stop();
	
	float faster();
	
	float slower();
	
	float delay();
	
	boolean isRunning();

}
