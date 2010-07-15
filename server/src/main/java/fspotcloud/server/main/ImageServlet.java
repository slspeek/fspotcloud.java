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
import javax.servlet.http.HttpServlet;

import com.google.appengine.api.datastore.Blob;

import fspotcloud.server.model.photo.Photo;
import fspotcloud.server.util.PMF;

@SuppressWarnings("serial")
public class ImageServlet extends HttpServlet {

	@Override
	public void service(ServletRequest request, ServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		boolean thumb = (request.getParameter("thumb") != null);
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Photo photo = (Photo) pm.getObjectById(Photo.class, id);
		Blob imageData = thumb ? photo.getThumb(): photo.getImage();
		response.setContentType("image/jpeg");
		OutputStream out =response.getOutputStream(); 
		out.write(imageData.getBytes());
		out.close();
	}
}
