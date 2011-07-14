package fspotcloud.client.place;

import junit.framework.TestCase;

public class BasePlaceTest extends TestCase {

	public void testEquals() {
		BasePlace first = new BasePlace("1", "2", 1, 1);
		BasePlace second = new BasePlace("1", "2");
		assertEquals(first, second);
	}
	
	public void testNotEqualsByColumns() {
		BasePlace first = new BasePlace("1", "2", 1, 1);
		BasePlace second = new BasePlace("1", "2", 2, 1);
		assertNotSame("different ni of columns", first, second);
	}
	
	public void testNotEqualsByRows() {
		BasePlace first = new BasePlace("1", "2", 2, 1);
		BasePlace second = new BasePlace("1", "2", 1, 1);
		assertNotSame("different ni of columns", first, second);
	}

	public void testNotEqualsByTree() {
		BasePlace first = new BasePlace("1", "2", 2, 1, true);
		BasePlace second = new BasePlace("1", "2", 1, 1, false);
		assertNotSame("different ni of columns", first, second);
	}
}
