package fspotcloud.server.main.task;

import static com.google.appengine.api.labs.taskqueue.TaskOptions.Builder.url;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

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

import fspotcloud.server.model.batch.Batch;
import fspotcloud.server.model.batch.Batches;
import fspotcloud.server.model.photo.Photo;
import fspotcloud.server.model.tag.Tag;
import fspotcloud.server.model.tag.TagReader;

@SuppressWarnings("serial")
@Singleton
public class TagViewTaskServlet extends HttpServlet {

	private static final Logger log = Logger.getLogger(TagViewTaskServlet.class
			.getName());

	@Inject
	private TagReader tagManager; 

	@Inject 
	private Batches batchManager;
	
	@Inject PersistenceManager pm;

	@SuppressWarnings("unchecked")
	@Override
	public void service(ServletRequest request, ServletResponse response)
			throws ServletException, IOException {

		String batchIdParam = request.getParameter("batchId");
		long batchId = Long.valueOf(batchIdParam);
		Batch batch = batchManager.getById(batchId);
		batch.incrementInterationCount();
		batchManager.save(batch);
		String minDateParam = request.getParameter("minDate");
		Date minDate = new Date(Long.valueOf(minDateParam));
		String tagId = request.getParameter("tagId");
		String maxTicksProp = System.getProperty("fspotcloud.max.data.ticks");
		int maxTicks = Integer.valueOf(maxTicksProp);

		log.info("TagId: now :: " + tagId);
		Tag tag = tagManager.getById(tagId);

		// Do our part of the job, scheduling the oldest images
		Query query = pm.newQuery(Photo.class);
		query.setFilter("imageLoaded == True && tagList == '" + tagId
				+ "' && date > dateParam");
		query.declareImports("import java.util.Date");
		query.declareParameters("Date dateParam");
		query.setOrdering("date");
		query.setRange(0, maxTicks);
		List<Photo> photos = (List<Photo>) query.execute(minDate);

		log.info("Interation: " + batch.getInterationCount() + " MinDate: " + minDate);
		if (!photos.isEmpty()) {
			Photo last = photos.get(photos.size() - 1);
			Date newMinDate = last.getDate();
			log.info("Lats Photo id: " + last.getName() + " NewMinDate: " + newMinDate);
			long newMinDateLong = newMinDate.getTime();
			for (Photo photo : photos) {
				if (!tag.getCachedPhotoList().contains(photo.getName())) {
					tag.getCachedPhotoList().add(photo.getName());
				} else {
					log.warning(photo.getName() + "was allready added?!");
				}
			}
			tagManager.save(tag);
			Queue queue = QueueFactory.getDefaultQueue();
			queue.add(url("/main/task/tagView").param("batchId", batchIdParam)
					.param("minDate", String.valueOf(newMinDateLong)).param(
							"tagId", tagId));
		} else {
			batch.stop();
			batchManager.save(batch);
		}
		

		PrintWriter out = response.getWriter();
		out.println("TagViewTask ran.");
		out.close();
	}

}
