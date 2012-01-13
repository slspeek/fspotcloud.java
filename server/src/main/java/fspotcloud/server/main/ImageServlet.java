package fspotcloud.server.main;

import java.io.IOException;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.Blob;
import com.google.inject.Inject;
import com.google.inject.Singleton;

import fspotcloud.server.model.api.Photo;
import fspotcloud.server.model.api.Photos;

/*
 * Courtesy to Felipe Gaucho
 */
@SuppressWarnings("serial")
@Singleton
public class ImageServlet extends HttpServlet {

	@Inject
	private Photos photoManager;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		boolean thumb = (request.getParameter("thumb") != null);
		Photo photo = photoManager.getById(id);
		byte[] imageData = thumb ? photo.getThumb() : photo.getImage();
		response.setContentType("image/jpeg");
		setCacheExpireDate(response, 3600 * 24 * 365);
		OutputStream out = response.getOutputStream();
		out.write(imageData);
		out.close();
	}

	public static void setCacheExpireDate(HttpServletResponse response,
			int seconds) {
		if (response != null) {
			Calendar cal = new GregorianCalendar();
			cal.roll(Calendar.SECOND, seconds);
			response.setHeader("Cache-Control", "PUBLIC, max-age=" + seconds
					+ ", must-revalidate");
			response.setHeader("Expires",
					htmlExpiresDateFormat().format(cal.getTime()));
		}
	}

	public static DateFormat htmlExpiresDateFormat() {
		DateFormat httpDateFormat = new SimpleDateFormat(
				"EEE, dd MMM yyyy HH:mm:ss z", Locale.US);
		httpDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
		return httpDateFormat;
	}
}
