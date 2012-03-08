package com.googlecode.fspotcloud.peer;

import com.googlecode.fspotcloud.peer.ImageData;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.logging.Logger;

import javax.imageio.ImageIO;

import junit.framework.TestCase;

public class ImageDataTest extends TestCase {
	private static final Logger log = Logger.getLogger(ImageDataTest.class
			.getName());
	private ImageData target;
	String cwd;
	
	protected void setUp() throws Exception {
		super.setUp();
		target = new ImageData();
		cwd = System.getProperty("user.dir");
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public final void testGetScaledImageData() throws Exception {
		String imgPath = "/src/test/resources/images/img_0659 (Gewijzigd).jpg";
		String path = cwd + imgPath;
		System.out.println(path);
		Dimension size = new Dimension(200, 100);
		byte[] data = target.getScaledImageData(path, size);
		InputStream dataStream = new ByteArrayInputStream(data);
		BufferedImage img = ImageIO.read(dataStream);
		int w = img.getWidth();
		int h = img.getHeight();
		assertEquals(133, w);
		assertEquals(100, h);
	}

	public final void testGetScaledImageDataPortrait() throws Exception {
		String urlString = cwd + "/src/test/resources/Photos/2010/06/04/Mac-classic.jpg";
		Dimension size = new Dimension(200, 100);
		byte[] data = target.getScaledImageData(urlString, size);
		InputStream dataStream = new ByteArrayInputStream(data);
		BufferedImage img = ImageIO.read(dataStream);
		int w = img.getWidth();
		int h = img.getHeight();
		assertEquals(75, w);
		assertEquals(100, h);
	}
	
}
