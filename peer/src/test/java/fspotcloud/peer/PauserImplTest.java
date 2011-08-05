package fspotcloud.peer;

import junit.framework.TestCase;

public class PauserImplTest extends TestCase {

	PauserImpl pauser = new PauserImpl();
	
	public void testPauseOneTenthSecond(){
		long before = System.currentTimeMillis();
		pauser.pause(100);
		long after = System.currentTimeMillis();
		assertTrue(after - before > 99);
	}
}
