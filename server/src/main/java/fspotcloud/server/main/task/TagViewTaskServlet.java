package fspotcloud.server.main.task;

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

import fspotcloud.server.model.api.Batch;
import fspotcloud.server.model.api.Batches;
import fspotcloud.server.model.api.Photo;
import fspotcloud.server.model.api.Photos;
import fspotcloud.server.model.api.Tag;
import fspotcloud.server.model.api.Tags;
import fspotcloud.shared.photo.PhotoInfo;

@SuppressWarnings("serial")
@Singleton
public class TagViewTaskServlet extends HttpServlet {

	private static final Logger log = Logger.getLogger(TagViewTaskServlet.class
			.getName());

	@Inject
	private Tags tagManager;

	@Inject
	private Batches batchManager;

	@Inject
	private Photos photoManager;

	@Inject
	@Named("maxTicks")
	private Integer maxTicks;

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

		log.info("TagId: now :: " + tagId);
		Tag tag = tagManager.getById(tagId);

		List<Photo> photos = photoManager.getPhotosForTagAfter(tagId, minDate,
				maxTicks);

		log.info("Iteration: " + batch.getInterationCount() + " MinDate: "
				+ minDate);
		if (!photos.isEmpty()) {
			Photo last = photos.get(photos.size() - 1);
			Date newMinDate = last.getDate();
			log.info("Last seen Photo id: " + last.getId() + " NewMinDate: "
					+ newMinDate);
			long newMinDateLong = newMinDate.getTime();
			for (Photo photo : photos) {
				PhotoInfo pi = new PhotoInfo(photo.getId(), photo
						.getDescription(), photo.getDate());
				tag.getCachedPhotoList().add(pi);

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
