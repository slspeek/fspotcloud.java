package fspotcloud.server.admin.task;

import static com.google.appengine.api.labs.taskqueue.TaskOptions.Builder.url;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;

import com.google.appengine.api.labs.taskqueue.Queue;
import com.google.appengine.api.labs.taskqueue.QueueFactory;

import fspotcloud.server.model.api.Batch;
import fspotcloud.server.model.api.Photo;
import fspotcloud.server.model.api.Photos;

public class PhotoCountTask implements Callable<Integer>, Serializable {

	private final PhotoCountTaskFactory hactory;
	private final Photos photoManager;
	private final Batch batch;
	private final int countUpToNow;
	private final Date lastSeenDate;
	private Integer maxCount;

	public PhotoCountTask(PhotoCountTaskFactory hackory, Batch batch,
			Photos photos, int countUpToNow, Date lastSeenDate, Integer maxCount) {
		this.hactory = hackory;
		this.batch = batch;
		this.photoManager = photos;
		this.countUpToNow = countUpToNow;
		this.lastSeenDate = lastSeenDate;
		this.maxCount = maxCount;
	}

	@Override
	public Integer call() throws Exception {
		batch.incrementInterationCount();
		List<Photo> result = photoManager.getPhotosStartingAtDate(lastSeenDate, maxCount);
		int resultCount = result.size();
		int newCount = countUpToNow + resultCount;
		batch.setResult(String.valueOf(newCount));

		boolean needToSchedule = !(resultCount < maxCount);
		if (needToSchedule) {
			Photo last = result.get(resultCount - 1);
			Date newMinDate = last.getDate();
			batch.setState(String.valueOf(newCount));
//			batchManager.save(batch);
			PhotoCountTask recursive = hactory.getTask(batch, newCount, newMinDate);
					
		} else {
			// We stop
			batch.stop();
			//batchManager.save(batch);
		}
		
		
		return newCount;
	}

}
