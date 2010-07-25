package fspotcloud.server.main;

import java.io.IOException;
import java.io.OutputStream;

import javax.jdo.PersistenceManager;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;

import com.google.appengine.api.datastore.Blob;
import com.google.inject.Inject;
import com.google.inject.Singleton;

import fspotcloud.server.model.photo.Photo;
import fspotcloud.server.model.photo.PhotoManager;

@SuppressWarnings("serial")
@Singleton
public class ImageServlet extends HttpServlet {

	@Inject 
	private PhotoManager photoManager;
	
	@Override
	public void service(ServletRequest request, ServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		boolean thumb = (request.getParameter("thumb") != null);
		Photo photo = photoManager.getById(id);
		Blob imageData = thumb ? photo.getThumb(): photo.getImage();
		response.setContentType("image/jpeg");
		OutputStream out =response.getOutputStream(); 
		out.write(imageData.getBytes());
		out.close();
	}
}
