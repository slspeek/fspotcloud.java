package fspotcloud.peer;

import java.awt.Dimension;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ImageData {

	final static private Logger log = Logger.getLogger(ImageData.class
			.getName());

	public byte[] getScaledImageData(String url, Dimension size)
			throws Exception {
		int width = (int) size.width;
		int height = (int) size.height;
		if(url.startsWith("file://")){
			url = url.substring(6);
		}
		String[] command = getCommand(url, width, height);
		log.info("About to run: " + Arrays.asList(command));
		Process convert = Runtime.getRuntime().exec(command);
		InputStream in = convert.getInputStream();
		BufferedInputStream bis = new BufferedInputStream(in);
		ByteArrayOutputStream bas = new ByteArrayOutputStream();
		try {
			int next = bis.read();
			while (next > -1) {
				bas.write(next);
				next = bis.read();
			}
			bas.flush();
		} catch (Exception e) {
			log.log(Level.SEVERE, "getScaledImageData threw: ", e);

		} finally {
			in.close();
			bas.close();
		}

		int returnValue = convert.waitFor();
		if (returnValue != 0) {
			throw new RuntimeException("convert ran on an error");
		}
		byte[] data = bas.toByteArray();
		return data;
	}

	private String[] getCommand(String path, int width, int height) {
		String[] result = new String[10];
		result[0] = "/usr/bin/convert";
		result[1] = "-auto-orient";
		result[2] = "-quality";
		result[3] = "50";
		result[4] = "-compress";
		result[5] = "JPEG";
		result[6] = "-geometry";
		result[7] = width + "x" + height;
		result[8] = path;
		result[9] = "-";
		return result;
	}
}
