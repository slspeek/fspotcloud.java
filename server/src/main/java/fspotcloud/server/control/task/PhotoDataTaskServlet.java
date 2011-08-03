package fspotcloud.server.control.task;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.name.Named;

@SuppressWarnings("serial")
@Singleton
public class PhotoDataTaskServlet extends HttpServlet {

	@Named("default") @Inject
	private PhotoDataScheduler scheduler;

	@Override
	public void service(ServletRequest request, ServletResponse response)
			throws ServletException, IOException {
		String startParam = request.getParameter("offset");
		String countParam = request.getParameter("limit");
		int start = Integer.valueOf(startParam);
		int count = Integer.valueOf(countParam);
		scheduler.schedulePhotoDataImport(start, count);
		PrintWriter out = response.getWriter();
		out.println("PhotoDataTask ran.");
		out.close();
	}
}
