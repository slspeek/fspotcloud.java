package fspotcloud.server.control.task;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import com.google.appengine.api.labs.taskqueue.Queue;
import com.google.appengine.api.labs.taskqueue.QueueFactory;
import static com.google.appengine.api.labs.taskqueue.TaskOptions.Builder.*;

import fspotcloud.server.control.Scheduler;

@SuppressWarnings("serial")
public class PhotoDataTaskServlet extends GenericServlet {

	@Override
	public void service(ServletRequest request, ServletResponse response)
			throws ServletException, IOException {
		String startParam = request.getParameter("offset");
		String countParam = request.getParameter("limit");
		String maxTicksProp = System.getProperty("fspotcloud.max.data.ticks");
		int start = Integer.valueOf(startParam);
		int count = Integer.valueOf(countParam);
		int maxTicks = Integer.valueOf(maxTicksProp);
		int countWeWillDo = -1;
		int needToScheduleCount = (int) Math.ceil((double) count
				/ (double) maxTicks);
		if (needToScheduleCount > maxTicks) {
			// Schedule the next task
			int maxTicksSquare = maxTicks * maxTicks;
			int nextStart = start + maxTicksSquare;
			int nextCount = count - maxTicksSquare;
			Queue queue = QueueFactory.getDefaultQueue();
			queue.add(url("/control/task/photo_data").param("offset",
					String.valueOf(nextStart)).param("limit",
					String.valueOf(nextCount)));
			countWeWillDo = maxTicks;
		} else {
			countWeWillDo = needToScheduleCount;
		}

		// Do our part of the job, scheduling the head
		for (int i = 0; i < countWeWillDo; i++) {
			int beginning = start + i * maxTicks;
			List<String> args = new ArrayList<String>();
			args.add(String.valueOf(beginning));
			args.add(maxTicksProp);
			Scheduler.schedule("sendPhotoData", args);
		}

		PrintWriter out = response.getWriter();

		out.println("PhotoDataTask ran.");
		out.close();
	}
}
