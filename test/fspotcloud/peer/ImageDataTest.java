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
		URL url = ClassLoader.getSystemResource("resources/images/img_0659.jpg");
		Dimension size = new Dimension(200, 100);
		byte[] data = target.getScaledImageData(url, size);
		InputStream dataStream = new ByteArrayInputStream(data);
		BufferedImage img = ImageIO.read(dataStream);
		int w = img.getWidth();
		int h = img.getHeight();
		assertEquals(size.width, w);
		assertEquals(size.height, h);
	}

}
