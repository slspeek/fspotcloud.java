package fspotcloud.client.demo;

public interface DemoStep {
	Runnable getAction();
	int pauseTime();
	String getDescription();
}
