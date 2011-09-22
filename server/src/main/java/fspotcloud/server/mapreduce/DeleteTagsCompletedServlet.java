package fspotcloud.server.mapreduce;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.hadoop.mapreduce.JobID;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.tools.mapreduce.MapReduceState;
import com.google.inject.Inject;
import com.google.inject.Singleton;

import fspotcloud.server.model.api.PeerDatabase;
import fspotcloud.server.model.api.PeerDatabases;

@SuppressWarnings("serial")
@Singleton
public class DeleteTagsCompletedServlet extends HttpServlet {

	@Inject
	private PeerDatabases peerDatabases;
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

		String jobIdName = req.getParameter("job_id");
		JobID jobId = JobID.forName(jobIdName);
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		try {
			MapReduceState mrState = MapReduceState.getMapReduceStateFromJobID(
					datastore, jobId);
			PeerDatabase peerDatabase = peerDatabases.get();
			peerDatabase.setCachedImportedTags(new ArrayList<String>());
			peerDatabases.save(peerDatabase);
			mrState.delete();
		} catch (EntityNotFoundException e) {
			throw new IOException("No datastore state");
		}

	}

}
