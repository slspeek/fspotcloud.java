package fspotcloud.client.view;

public interface TimerInterface {

	void scheduleRepeating(int millis);

	void cancel();

	void setRunnable(Runnable runnable);
}
