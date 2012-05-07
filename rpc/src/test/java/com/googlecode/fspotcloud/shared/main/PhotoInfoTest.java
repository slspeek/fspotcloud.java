/*
 * Copyright 2010-2012 Steven L. Speek.
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 *
 */
package com.googlecode.fspotcloud.shared.main;

import com.googlecode.fspotcloud.shared.main.PhotoInfo;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.SortedSet;
import java.util.TreeSet;
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

    public void testEqualDates() throws Exception {
        PhotoInfo info1 = new PhotoInfo("1", "", new Date(0));
        PhotoInfo info2 = new PhotoInfo("2", "", new Date(0));

        assertEquals(-1, info1.compareTo(info2));
    }

    public void testEqualDatesEqualIds() throws Exception {
        PhotoInfo info1 = new PhotoInfo("1", "", new Date(0));
        PhotoInfo info2 = new PhotoInfo("1", "", new Date(0));

        assertEquals(0, info1.compareTo(info2));
    }

    public void testEquals() throws Exception {
        assertTrue(ape.equals(ape));
        assertFalse(ape.equals(man));
        assertFalse(man.equals(ape));
        assertTrue(ape.equals(new PhotoInfo(photoApe, "", new Date())));
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

    public void testVersion() {
        int v = man.getVersion();
        assertEquals(1, v);
    }

    public void toInSortedSet() {
        SortedSet<PhotoInfo> set = new TreeSet<PhotoInfo>();
        set.add(man);
        set.add(ape);
        set.remove(man);
        set.remove(ape);
        assertEquals(0, set.size());
    }
}
