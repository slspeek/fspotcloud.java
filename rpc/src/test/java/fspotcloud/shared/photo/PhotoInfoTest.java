package fspotcloud.shared.photo;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
	String exif = "EXIF:";

	protected void setUp() throws Exception {
		longAgo = formatter.parse("20100101");
		ago = formatter.parse("20100102");
		ape = new PhotoInfo(photoApe, "Ape", longAgo);
		man = new PhotoInfo(photoMan, "Human", ago, exif);
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
		assertTrue(ape.equals(photoApe));
		assertTrue(ape.equals(ape));
		assertFalse(ape.equals(man));
		assertFalse(man.equals(ape));
		assertTrue(ape.equals(new PhotoInfo(photoApe, "", new Date())));
	}

	public void testEqualsObjectToAString() throws Exception {
		assertTrue(man.equals(photoMan));
	}

	public void testHashCode() {
		PhotoInfo sameMan = new PhotoInfo(photoMan, "Foo", new Date(), "Bar");
		assertEquals(sameMan.hashCode(), man.hashCode());
		assertEquals(sameMan, man);
	}
	
	public void testExif() {
		assertNull(ape.getExifData());
		ape.setExifData(exif);
		assertEquals(exif, ape.getExifData());
	}
	
	public void testToString() {
		String s = man.toString();
		assertEquals("PhotoInfo(2)", s);
	}
	
	public void testSerialization() throws IOException, ClassNotFoundException {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		ObjectOutputStream objectOut = new ObjectOutputStream(out);
		objectOut.writeObject(ape);
		InputStream in = new ByteArrayInputStream(out.toByteArray());
		ObjectInputStream objectIn = new ObjectInputStream(in);
		PhotoInfo apeReadBack = (PhotoInfo) objectIn.readObject();
		assertEquals(ape, apeReadBack);
	}
}
