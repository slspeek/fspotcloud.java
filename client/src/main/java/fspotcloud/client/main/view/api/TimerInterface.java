package fspotcloud.client.main.view.api;

public interface TimerInterface {

	void scheduleRepeating(int millis);
	
	void schedule(int millis);

	void cancel();

	void setRunnable(Runnable runnable);
}
