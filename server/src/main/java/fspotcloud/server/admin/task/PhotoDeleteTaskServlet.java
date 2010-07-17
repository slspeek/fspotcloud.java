package fspotcloud.server.admin.task;

import static com.google.appengine.api.labs.taskqueue.TaskOptions.Builder.url;

import java.io.IOException;
import java.io.PrintWriter;
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
import fspotcloud.server.model.batch.BatchManager;
import fspotcloud.server.model.peerdatabase.DefaultPeer;
import fspotcloud.server.model.peerdatabase.PeerDatabase;
import fspotcloud.server.model.photo.Photo;
import fspotcloud.server.model.photo.PhotoManager;

@SuppressWarnings("serial")
@Singleton
public class PhotoDeleteTaskServlet extends HttpServlet {

	private static final Logger log = Logger
			.getLogger(PhotoDeleteTaskServlet.class.getName());
	
	@Inject
	private BatchManager batchManager;
	@Inject
	private PhotoManager photoManager;
	@Inject
	private DefaultPeer defaultPeer;
	
	@SuppressWarnings("unchecked")
	@Override
	public void service(ServletRequest request, ServletResponse response)
			throws ServletException, IOException {
		String batchIdParam = request.getParameter("batchId");
		long batchId = Long.valueOf(batchIdParam);
		String deleteCountParam = request.getParameter("deleteCount");
		int deleteCount = Integer.valueOf(deleteCountParam);
		
		Batch batch = batchManager.getById(batchId);
		batch.incrementInterationCount();
		
		List<Photo> result = photoManager.getOldestPhotosChunk();
		int resultCount = result.size();
		photoManager.deleteAll(result);
		
		
		int newDeleteCount = deleteCount + resultCount;
		batch.setResult(String.valueOf(newDeleteCount));
		batchManager.save(batch);

		result = photoManager.getOldestPhotosChunk();
		if (!result.isEmpty()) {
			//hasPhotos left
			log.info("We reschedule a task " + batch.getKey());
			Queue queue = QueueFactory.getDefaultQueue();
			queue.add(url("/admin/task/photoDelete").param("deleteCount",
					String.valueOf(newDeleteCount)).param("batchId",
					String.valueOf(batchId)));
			log.info("We rescheduled");

		} else {
			// We stop
			log.info("We stop" + batch.getKey());
			PeerDatabase pd = defaultPeer.get();
			pd.setCount(0);
			defaultPeer.save(pd);
			batch.stop();
			batchManager.save(batch);
		}
		PrintWriter out = response.getWriter();
		out.println("PhotoDelete task ran.");
		out.close();
	}

}
