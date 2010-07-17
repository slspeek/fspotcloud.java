package fspotcloud.server.control.task;

import static com.google.appengine.api.labs.taskqueue.TaskOptions.Builder.url;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;

import com.google.appengine.api.labs.taskqueue.Queue;
import com.google.appengine.api.labs.taskqueue.QueueFactory;
import com.google.inject.Inject;
import com.google.inject.Singleton;

import fspotcloud.server.control.Scheduler;
import fspotcloud.server.model.photo.Photo;

@SuppressWarnings("serial")
@Singleton
public class ImageDataTaskServlet extends HttpServlet {

	
	@Inject private Scheduler scheduler;
	@Inject PersistenceManager pm;

	@SuppressWarnings("unchecked")
	@Override
	public void service(ServletRequest request, ServletResponse response)
			throws ServletException, IOException {
		String minDateParam = request.getParameter("minDate");
		Date minDate = new Date(Long.valueOf(minDateParam));
		String countParam = request.getParameter("maxCount");
		int count = Integer.valueOf(countParam);
		String tagId = request.getParameter("tagId");
		String maxTicksProp = System.getProperty("fspotcloud.max.data.ticks");
		int maxTicks = Integer.valueOf(maxTicksProp);

	
		// Do our part of the job, scheduling the oldest images
		
		Query query = pm.newQuery(Photo.class);
		query.setFilter("image == null && tagList == '" + tagId
				+ "' && date > dateParam");
		query.declareImports("import java.util.Date");
		query.declareParameters("Date dateParam");
		query.setOrdering("date");
		query.setRange(0, maxTicks);
		List<Photo> result = (List<Photo>) query.execute(minDate);

		for (Photo photo : result) {
			List<String> args = new ArrayList<String>();
			args.add(photo.getName());
			args.add("640");
			args.add("480");
			scheduler.schedule("sendImageData", args);
		}
		if (!result.isEmpty()) {
			Photo last = result.get(result.size() - 1);
			Date newMinDate = last.getDate();
			long newMinDateLong = newMinDate.getTime();
			boolean needToSchedule = count > maxTicks;
			if (needToSchedule) {
				// Schedule the next task
				int nextCount = count - maxTicks;
				Queue queue = QueueFactory.getDefaultQueue();
				queue.add(url("/control/task/imageData").param("minDate",
						String.valueOf(newMinDateLong)).param("maxCount",
						String.valueOf(nextCount)).param("tagId", tagId));

			}
		}
		PrintWriter out = response.getWriter();
		out.println("ImageDataTask ran.");
		out.close();
	}

}
