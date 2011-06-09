package fspotcloud.peer;

import java.awt.Dimension;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.net.URL;

public class ImageData {

	public byte[] getScaledImageData(URL url, Dimension size) throws Exception {
		int width = (int) size.width;
		int height = (int) size.height;
		File inp = new File(url.toURI());
		String command = getCommand(inp.getAbsolutePath(), width, height);
		System.out.println(command);
		Process convert = Runtime.getRuntime().exec(command);
		InputStream in = convert.getInputStream(); 
		BufferedInputStream bis = new BufferedInputStream(
				in);
		ByteArrayOutputStream bas = new ByteArrayOutputStream();
		try {
		int next = bis.read();
		while (next > -1) {
			bas.write(next);
			next = bis.read();
		}
		bas.flush();
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			in.close();
			bas.close();
		}
		byte[] data = bas.toByteArray();
		return data;
	}

	private String getCommand(String path, int width, int height) {
		String cmd = "/usr/bin/convert.exe -auto-orient  -quality 50 -compress JPEG -geometry  " + width + "x" + height + " "
				+ path + " -";
		return cmd;
	}
}
