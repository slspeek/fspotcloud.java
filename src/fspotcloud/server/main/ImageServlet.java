package fspotcloud.server.main;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Collections;

import javax.jdo.PersistenceManager;
import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.google.appengine.api.datastore.Blob;

import fspotcloud.server.photo.Photo;
import fspotcloud.server.util.PMF;

@SuppressWarnings("serial")
public class ImageServlet extends GenericServlet {

	@Override
	public void service(ServletRequest request, ServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Photo photo = (Photo) pm.getObjectById(Photo.class, id);
		Blob imageData = photo.getImage();
		response.setContentType("image/jpeg");
		OutputStream out =response.getOutputStream(); 
		out.write(imageData.getBytes());
		out.close();
	}
}
