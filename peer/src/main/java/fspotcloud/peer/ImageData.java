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
		double width = size.width;
		double height = size.height;
		img = ImageIO.read(url);
		
		double scaleFactor = 1.0; 
		double srcWidth = (double) img.getWidth();
		double srcHeight = (double) img.getHeight();
		double targetAspectRatio = (double) width / (double) height;
		double sourceAspectRatio = srcWidth / srcHeight;
		if (targetAspectRatio < sourceAspectRatio) {
			scaleFactor = (double) width/ srcWidth;
		} else {
			scaleFactor = (double) height / srcHeight;
		}
		int targetWidth = (int) (scaleFactor * srcWidth);
		int targetHeight = (int) (scaleFactor * srcHeight);
		BufferedImage scaledImg = new BufferedImage(targetWidth, targetHeight,
				BufferedImage.TYPE_INT_RGB);
		Graphics2D g = scaledImg.createGraphics();
		AffineTransform at = AffineTransform.getScaleInstance(scaleFactor, scaleFactor);
		g.drawRenderedImage(img, at);
		ByteArrayOutputStream bas = new ByteArrayOutputStream();
		ImageIO.write(scaledImg, "jpg", bas);
		byte[] data = bas.toByteArray();
		return data;
	}
}
