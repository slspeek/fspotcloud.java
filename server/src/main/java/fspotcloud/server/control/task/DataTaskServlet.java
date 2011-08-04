package fspotcloud.server.control.task;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;

import com.google.inject.Inject;
import com.google.inject.Singleton;

@SuppressWarnings("serial")
@Singleton
public class DataTaskServlet extends HttpServlet {

	@Inject
	private DefaultDataSchedulerFactory schedulerFactory;

	@Override
	public void service(ServletRequest request, ServletResponse response)
			throws ServletException, IOException {
		String startParam = request.getParameter("offset");
		String countParam = request.getParameter("limit");
		String kind = request.getParameter("kind");
		int start = Integer.valueOf(startParam);
		int count = Integer.valueOf(countParam);
		DataScheduler scheduler = schedulerFactory.get(kind);
		scheduler.scheduleDataImport(start, count);
		PrintWriter out = response.getWriter();
		out.println("DataTask ran.");
		out.close();
	}
}
