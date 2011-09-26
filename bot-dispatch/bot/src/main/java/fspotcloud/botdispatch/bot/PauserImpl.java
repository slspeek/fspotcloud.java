package fspotcloud.botdispatch.bot;

public class PauserImpl implements Pauser {
	public void pause(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
		}
	}
}
