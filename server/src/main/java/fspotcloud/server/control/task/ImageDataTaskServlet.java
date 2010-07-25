package fspotcloud.server.control.task;

import static com.google.appengine.api.labs.taskqueue.TaskOptions.Builder.url;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;

import com.google.appengine.api.labs.taskqueue.Queue;
import com.google.appengine.api.labs.taskqueue.QueueFactory;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.name.Named;

import fspotcloud.server.control.Scheduler;
import fspotcloud.server.model.photo.PhotoDO;
import fspotcloud.server.model.photo.PhotoManager;

@SuppressWarnings("serial")
@Singleton
public class ImageDataTaskServlet extends HttpServlet {

	@Inject
	private Scheduler scheduler;
	@Inject @Named("maxTicks")
	private Integer maxTicks;
	@Inject
	private PhotoManager photoManager;

	@SuppressWarnings("unchecked")
	@Override
	public void service(ServletRequest request, ServletResponse response)
			throws ServletException, IOException {
		String minDateParam = request.getParameter("minDate");
		Date minDate = new Date(Long.valueOf(minDateParam));
		String countParam = request.getParameter("maxCount");
		int count = Integer.valueOf(countParam);
		String tagId = request.getParameter("tagId");

		// Do our part of the job, scheduling the oldest images
		List<PhotoDO> result = photoManager.getEmptyPhotosForTagAfter(tagId,
				minDate, maxTicks);

		for (PhotoDO photo : result) {
			List<String> args = new ArrayList<String>();
			args.add(photo.getName());
			args.add("800");
			args.add("600");
			scheduler.schedule("sendImageData", args);
		}
		if (!result.isEmpty()) {
			PhotoDO last = result.get(result.size() - 1);
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
