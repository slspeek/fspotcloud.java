package fspotcloud.peer;

import junit.framework.TestCase;

public class NamingUtilTest extends TestCase {

	NamingUtil util = new NamingUtil();

	public void testNotValidateMethod() {
		try {
			util.validateMethod("foo");
			fail();
		} catch (MethodNotFoundException e) {
		}
	}

	public void testValidateMethod() throws MethodNotFoundException {
			util.validateMethod("sendMetaData");
	}

	public void testGetDataMethod() throws MethodNotFoundException {
		assertEquals("getMetaData", util.getDataMethod("sendMetaData"));
	}

	public void testGetRemoteMethod() throws MethodNotFoundException {
		assertEquals("MetaReciever.recieveMetaData", util.getRemoteMethod("sendMetaData"));
	}
	
	public void testGetRemoteMethodImageData() throws MethodNotFoundException {
		assertEquals("ImageReciever.recieveImageData", util.getRemoteMethod("sendImageData"));
	}

}
