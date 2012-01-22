package fspotcloud.client.place;

import junit.framework.TestCase;

public class TokenizerTest extends TestCase {

	BasePlace.Tokenizer tokenizer = new BasePlace.Tokenizer(); 
	
	public void testGetPlace() {
		BasePlace expected = new BasePlace("1", "2", 1, 1);
		String  first = "1:2:1:1";
		BasePlace actual = tokenizer.getPlace(first);
		assertEquals(actual, expected);
	}

	public void testGetToken() {
		BasePlace first = new BasePlace("1", "2", 1, 1);
		String expected = "1:2:1:1";
		String actual = tokenizer.getToken(first);
		assertEquals(actual, expected);
	}

}
