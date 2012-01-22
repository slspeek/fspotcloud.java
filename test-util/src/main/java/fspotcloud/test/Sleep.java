package fspotcloud.test;

public class Sleep {

	public static void sleepShort() throws InterruptedException {
		sleepShort(1);
	}
	
	public static void sleepShort(int times) throws InterruptedException {
		Thread.sleep(900 * times);
	}
}
