package fspotcloud.server.control.task;

import static com.google.appengine.api.labs.taskqueue.TaskOptions.Builder.url;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.google.appengine.api.labs.taskqueue.Queue;
import com.google.appengine.api.labs.taskqueue.QueueFactory;

import fspotcloud.server.control.Scheduler;
import fspotcloud.server.model.photo.Photo;
import fspotcloud.server.util.PMF;

@SuppressWarnings("serial")
public class ImageDataTaskServlet extends GenericServlet {

	@Override
	public void service(ServletRequest request, ServletResponse response)
			throws ServletException, IOException {
		String startParam = request.getParameter("offset");
		String countParam = request.getParameter("limit");
		String tagId = request.getParameter("tagId");
		String maxTicksProp = System.getProperty("fspotcloud.max.data.ticks");
		int start = Integer.valueOf(startParam);
		int count = Integer.valueOf(countParam);

		int maxTicks = Integer.valueOf(maxTicksProp);
		int countWeWillDo = -1;

		// TODO query photo data
		// Do our part of the job, scheduling the head
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Query query = pm.newQuery(Photo.class);
		query.setFilter("image == null && tagList == '"	+ tagId + "'");
		query.setOrdering("date");
		List<Photo> result = (List<Photo>) query.execute();

		for (Photo photo : result) {
			List<String> args = new ArrayList<String>();
			args.add(photo.getName());
			args.add("640");
			args.add("480");
			Scheduler.schedule("sendImageData", args);
		}

		boolean needToSchedule = count > maxTicks;
		if (needToSchedule) {
			// Schedule the next task
			int nextStart = start + maxTicks;
			int nextCount = count - maxTicks;
			Queue queue = QueueFactory.getDefaultQueue();
			queue.add(url("/control/task/image_data").param("offset",
					String.valueOf(nextStart)).param("limit",
					String.valueOf(nextCount)).param("tagId", tagId));
			countWeWillDo = maxTicks;
		} else {
			countWeWillDo = count;
		}

		PrintWriter out = response.getWriter();
		out.println("ImageDataTask ran.");
		out.close();
	}

}
