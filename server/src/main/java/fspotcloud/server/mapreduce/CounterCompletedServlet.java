package fspotcloud.server.mapreduce;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.hadoop.mapreduce.Counter;
import org.apache.hadoop.mapreduce.CounterGroup;
import org.apache.hadoop.mapreduce.Counters;
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
public class CounterCompletedServlet extends HttpServlet {

	@Inject
	private PeerDatabases peerDatabases;
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

		String jobIdName = req.getParameter("job_id");
		JobID jobId = JobID.forName(jobIdName);

		// A future iteration of this will likely contain a default
		// option if we don't care which DatastoreService instance we use.
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		try {

			// We get the state back from the job_id parameter. The state is
			// serialized and stored in the datastore, so we pass an instance
			// of the datastore service.
			MapReduceState mrState = MapReduceState.getMapReduceStateFromJobID(
					datastore, jobId);

			// There's a bit of ceremony to get the actual counter. This
			// example is intentionally verbose for clarity. First get all the
			// Counters,
			// then we get the CounterGroup, then we get the Counter, then we
			// get the count.
			Counters counters = mrState.getCounters();
			CounterGroup counterGroup = counters.getGroup("Entity");
			Counter counter = counterGroup.findCounter("Total");
			long count = counter.getValue(); // Finally!

			PeerDatabase peerDatabase = peerDatabases.get();
			peerDatabase.setPhotoCount(count);
			peerDatabases.save(peerDatabase);
		} catch (EntityNotFoundException e) {
			throw new IOException("No datastore state");
		}

	}

}
