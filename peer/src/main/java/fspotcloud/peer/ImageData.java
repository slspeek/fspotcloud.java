package fspotcloud.peer;

import java.awt.Dimension;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.net.URL;

public class ImageData {

	public byte[] getScaledImageData(URL url, Dimension size) throws Exception {
		int width = (int) size.width;
		int height = (int) size.height;
		File inp = new File(url.toURI());
		String command = getCommand(inp.getAbsolutePath(), width, height);
		System.out.println(command);
		Process convert = Runtime.getRuntime().exec(command);
		BufferedInputStream bis = new BufferedInputStream(
				convert.getInputStream());
		ByteArrayOutputStream bas = new ByteArrayOutputStream();
		int next = bis.read();
		while (next > -1) {
			bas.write(next);
			next = bis.read();
		}
		bas.flush();
		byte[] data = bas.toByteArray();
		return data;
	}

	private String getCommand(String path, int width, int height) {
		String cmd = "/usr/bin/convert -geometry " + width + "x" + height + " "
				+ path + " -";
		return cmd;
	}
}
