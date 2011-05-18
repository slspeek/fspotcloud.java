package fspotcloud.peer;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;

import junit.framework.TestCase;

public class ImageDataTest extends TestCase {

	private ImageData target;
	
	protected void setUp() throws Exception {
		super.setUp();
		target = new ImageData();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public final void testGetScaledImageData() throws IOException {
		URL url = ClassLoader.getSystemResource("images/img_0659.jpg");
		Dimension size = new Dimension(200, 100);
		byte[] data = target.getScaledImageData(url, size);
		InputStream dataStream = new ByteArrayInputStream(data);
		BufferedImage img = ImageIO.read(dataStream);
		int w = img.getWidth();
		int h = img.getHeight();
		assertEquals(133, w);
		assertEquals(100, h);
	}

	public final void testGetScaledImageDataPortrait() throws IOException {
		URL url = ClassLoader.getSystemResource("Photos/2010/06/04/Mac-classic.jpg");
		Dimension size = new Dimension(200, 100);
		byte[] data = target.getScaledImageData(url, size);
		InputStream dataStream = new ByteArrayInputStream(data);
		BufferedImage img = ImageIO.read(dataStream);
		int w = img.getWidth();
		int h = img.getHeight();
		assertEquals(75, w);
		assertEquals(100, h);
	}

}
