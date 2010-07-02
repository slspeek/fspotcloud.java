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
import fspotcloud.server.model.batch.BatchManager;
import fspotcloud.server.model.photo.Photo;
import fspotcloud.server.model.photo.PhotoManager;
import fspotcloud.server.util.PMF;

@SuppressWarnings("serial")
public class PhotoDeleteTaskServlet extends GenericServlet {

	private static final Logger log = Logger
			.getLogger(PhotoDeleteTaskServlet.class.getName());
	private static final long STEP = 450;
	private BatchManager batchManager = new BatchManager();
	private PhotoManager photoManager = new PhotoManager();
	
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
		
		PersistenceManager pm = PMF.get().getPersistenceManager();
		List<Photo> result = photoManager.getOldestPhotosChunk(pm);
		int resultCount = result.size();
		pm.deletePersistentAll(result);
		pm.close();
		
		int newDeleteCount = deleteCount + resultCount;
		batch.setResult(String.valueOf(newDeleteCount));
		batchManager.save(batch);

		pm = PMF.get().getPersistenceManager();
		result = photoManager.getOldestPhotosChunk(pm);
		if (!result.isEmpty()) {
			//hasPhotos left
			Queue queue = QueueFactory.getDefaultQueue();
			queue.add(url("/admin/task/photoDelete").param("deleteCount",
					String.valueOf(newDeleteCount)).param("batchId",
					String.valueOf(batchId)));

		} else {
			// We stop
			batch.stop();
			batchManager.save(batch);
		}
		PrintWriter out = response.getWriter();
		out.println("PhotoDelete task ran.");
		out.close();
	}

}
