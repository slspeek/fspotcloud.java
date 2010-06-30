package fspotcloud.server.admin.task;

import static com.google.appengine.api.labs.taskqueue.TaskOptions.Builder.url;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.google.appengine.api.labs.taskqueue.Queue;
import com.google.appengine.api.labs.taskqueue.QueueFactory;

import fspotcloud.server.model.batch.Batch;
import fspotcloud.server.model.photo.Photo;
import fspotcloud.server.util.PMF;

@SuppressWarnings("serial")
public class PhotoCountTaskServlet extends GenericServlet {

	private static final Logger log = Logger
			.getLogger(PhotoCountTaskServlet.class.getName());
	private static final long STEP = 450;

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

		PersistenceManager pm = PMF.get().getPersistenceManager();
		Batch batch = null;

		batch = pm.getObjectById(Batch.class, batchId);
		batch.incrementInterationCount();
		pm.close();
		
		pm = PMF.get().getPersistenceManager();
		Query query = pm.newQuery(Photo.class);
		query.setFilter(" date > dateParam");
		query.declareImports("import java.util.Date");
		query.declareParameters("Date dateParam");
		query.setOrdering("date");
		query.setRange(0, STEP);
		List<Photo> result = (List<Photo>) query.execute(minDate);
		int resultCount = result.size();
		pm.close();
		int newCount = count + resultCount;
		batch.setResult(String.valueOf(newCount));

		boolean needToSchedule = !(resultCount < STEP);
		if (needToSchedule) {
			Photo last = result.get(resultCount - 1);
			Date newMinDate = last.getDate();
			long newMinDateLong = newMinDate.getTime();
			batch.setState(String.valueOf(newCount));
			pm = PMF.get().getPersistenceManager();
			pm.makePersistent(batch);
			pm.close();
			Queue queue = QueueFactory.getDefaultQueue();
			queue.add(url("/admin/task/photoCount").param("minDate",
					String.valueOf(newMinDateLong)).param("count",
					String.valueOf(newCount)).param("batchId",
					String.valueOf(batchId)));

		} else {
			// We stop
			pm = PMF.get().getPersistenceManager();
			batch.stop();
			pm.makePersistent(batch);
			pm.close();
		}
		PrintWriter out = response.getWriter();
		out.println("PhotoCount task ran.");
		out.close();
	}

}
