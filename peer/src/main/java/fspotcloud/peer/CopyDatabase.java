package fspotcloud.peer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Provider;

public class CopyDatabase implements Provider<String> {

	@Inject
	@Named("DatabasePath")
	private String srcPath;

	@Inject
	@Named("WorkDir")
	private String pwd;


	public String copyDatabase() throws IOException {
            System.out.println("src: " +srcPath);
		File srcFile = new File(srcPath);
		File targetDir = new File(pwd + "/runtime");
		if (!targetDir.exists()) {
			targetDir.mkdir();
		}
		File targetFile = new File(targetDir.getAbsolutePath() + "/copy.db");
		InputStream in = new FileInputStream(srcFile);
		OutputStream out = new FileOutputStream(targetFile);
		byte[] buf = new byte[1024];
		int len;
		while ((len = in.read(buf)) > 0) {
			out.write(buf, 0, len);
		}
		in.close();
		out.close();
                String url = "jdbc:sqlite:" + targetFile.getAbsolutePath();
                System.out.println("url: " + url);
		return url;
	}

	@Override
	public String get() {
		String destPath = null;
		try {
			destPath = copyDatabase();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return destPath;
	}

}
