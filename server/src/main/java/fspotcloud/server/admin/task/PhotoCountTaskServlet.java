package fspotcloud.server.admin.task;

import static com.google.appengine.api.labs.taskqueue.TaskOptions.Builder.url;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;

import com.google.appengine.api.labs.taskqueue.Queue;
import com.google.appengine.api.labs.taskqueue.QueueFactory;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.name.Named;

import fspotcloud.server.model.batch.Batch;
import fspotcloud.server.model.batch.Batches;
import fspotcloud.server.model.photo.Photo;
import fspotcloud.server.model.photo.PhotoManager;

@SuppressWarnings("serial")
@Singleton
public class PhotoCountTaskServlet extends HttpServlet {

	private static final Logger log = Logger
			.getLogger(PhotoCountTaskServlet.class.getName());
	
	@Inject @Named("maxCount")
	private int STEP; 
	@Inject
	private Batches batchManager;
	@Inject
	private PhotoManager photoManager;

	@SuppressWarnings("unchecked")
	@Override
	public void service(ServletRequest request, ServletResponse response)
			throws ServletException, IOException {
		String batchIdParam = request.getParameter("batchId");
		long batchId = Long.valueOf(batchIdParam);
		String minDateParam = request.getParameter("minDate");
		Date minDate = new Date(Long.valueOf(minDateParam));
		String countParam = request.getParameter("count");
		int count = Integer.valueOf(countParam);

		Batch batch = batchManager.getById(batchId);
		batch.incrementInterationCount();
		
		List<Photo> result = photoManager.getPhotosStartingAtDate(minDate, STEP);
		int resultCount = result.size();
		int newCount = count + resultCount;
		batch.setResult(String.valueOf(newCount));

		boolean needToSchedule = !(resultCount < STEP);
		if (needToSchedule) {
			Photo last = result.get(resultCount - 1);
			Date newMinDate = last.getDate();
			long newMinDateLong = newMinDate.getTime();
			batch.setState(String.valueOf(newCount));
			batchManager.save(batch);
			Queue queue = QueueFactory.getDefaultQueue();
			queue.add(url("/admin/task/photoCount").param("minDate",
					String.valueOf(newMinDateLong)).param("count",
					String.valueOf(newCount)).param("batchId",
					String.valueOf(batchId)));

		} else {
			// We stop
			batch.stop();
			batchManager.save(batch);
		}
		PrintWriter out = response.getWriter();
		out.println("PhotoCount task ran.");
		out.close();
	}

}
