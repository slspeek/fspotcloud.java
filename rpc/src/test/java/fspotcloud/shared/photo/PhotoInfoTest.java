package fspotcloud.shared.photo;

import java.text.SimpleDateFormat;
import java.util.Date;

import junit.framework.TestCase;

public class PhotoInfoTest extends TestCase {

	SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
	Date longAgo;
	Date ago;
	PhotoInfo ape;
	PhotoInfo man;
	String photoApe = "1";
	String photoMan = "2";

	protected void setUp() throws Exception {
		longAgo = formatter.parse("20100101");
		ago = formatter.parse("20100102");
		ape = new PhotoInfo(photoApe, "Ape", longAgo);
		man = new PhotoInfo(photoMan, "Human", ago);
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testCompareToSmaller() throws Exception {
		assertEquals(-1, ape.compareTo(man));
	}

	public void testCompareToEquals() throws Exception {
		assertEquals(0, ape.compareTo(ape));
		assertEquals(0, man.compareTo(man));
	}
	public void testCompareToLarger() throws Exception {
		assertEquals(1, man.compareTo(ape));
	}
	public void testEquals() throws Exception {
		assertTrue(ape.equals(ape));
		assertFalse(ape.equals(man));
		assertFalse(man.equals(ape));
		assertTrue(ape.equals(new PhotoInfo(photoApe, "", new Date())));
	}

	public void testEqualsObjectToAString() throws Exception {
		assertTrue(man.equals(photoMan));
	}

	public void testToString() {
		String s = man.toString();
		System.out.println(s);
		assertEquals("PhotoInfo(2, Sat Jan 02 00:00:00 CET 2010)", s);
	}
}
