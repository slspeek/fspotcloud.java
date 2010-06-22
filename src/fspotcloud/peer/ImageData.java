package fspotcloud.peer;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

public class ImageData {

	public byte[] getScaledImageData(URL url, Dimension size)
			throws IOException {
		BufferedImage img = null;
		int width = size.width;
		int height = size.height;
		img = ImageIO.read(url);
		BufferedImage scaledImg = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);
		Graphics2D g = scaledImg.createGraphics();
		AffineTransform at = AffineTransform.getScaleInstance((double) width
				/ img.getWidth(), (double) height / img.getHeight());
		g.drawRenderedImage(img, at);
		ByteArrayOutputStream bas = new ByteArrayOutputStream();
		ImageIO.write(scaledImg, "jpg", bas);
		byte[] data = bas.toByteArray();
		return data;
	}
}
