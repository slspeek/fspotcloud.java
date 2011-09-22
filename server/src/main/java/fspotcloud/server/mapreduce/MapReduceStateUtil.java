package fspotcloud.server.mapreduce;

import static com.google.appengine.api.datastore.FetchOptions.Builder.withPrefetchSize;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import org.apache.hadoop.mapreduce.JobID;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.QueryResultIterator;
import com.google.inject.Inject;

public class MapReduceStateUtil implements MapReduceInfo {
	private static final Logger log = Logger
	.getLogger(MapReduceStateUtil.class.getName());
	
	// Property names
	public static final String ACTIVE_SHARD_COUNT_PROPERTY = "activeShardCount";
	public static final String CHART_PROPERTY = "chart";
	public static final String CONFIGURATION_PROPERTY = "configuration";
	public static final String COUNTERS_MAP_PROPERTY = "countersMap";
	public static final String LAST_POLL_TIME_PROPERTY = "lastPollTime";
	public static final String NAME_PROPERTY = "name";
	public static final String PROGRESS_PROPERTY = "progress";
	public static final String SHARD_COUNT_PROPERTY = "shardCount";
	public static final String START_TIME_PROPERTY = "startTime";
	public static final String STATUS_PROPERTY = "status";

	/**
	 * Possible states of the status property
	 */
	public static enum Status {
		ACTIVE, DONE
	}

	final private DatastoreService service;

	@Inject
	public MapReduceStateUtil(DatastoreService service) {
		super();
		this.service = service;
	}

	public Entity getFromJobID(JobID jobId) throws EntityNotFoundException {
		Key key = KeyFactory.createKey("MapReduceState", jobId.toString());
		Entity entity = service.get(key);
		return entity;
	}

	public List<Entity> getActiveStates(int count, String name) {
		FetchOptions fetchOptions = withPrefetchSize(count).limit(count);
		Calendar rightNow = Calendar.getInstance();
		rightNow.roll(Calendar.HOUR, false);
		Date oneHourAgo = rightNow.getTime();
		QueryResultIterator<Entity> stateEntitiesIt = service.prepare(
				new Query("MapReduceState")
						.addFilter(STATUS_PROPERTY, Query.FilterOperator.EQUAL,
								Status.ACTIVE.toString())
						.addFilter(NAME_PROPERTY, Query.FilterOperator.EQUAL,
								name)
						/*.addFilter(START_TIME_PROPERTY,
								Query.FilterOperator.GREATER_THAN, oneHourAgo)*/)
				.asQueryResultIterator(fetchOptions);
		List<Entity> states = new ArrayList<Entity>();
		while (stateEntitiesIt.hasNext()) {
			states.add(stateEntitiesIt.next());
		}
		return states;
	}

	@Override
	public int activeCount(String mapperName) {
		List<Entity> list = getActiveStates(100, mapperName); 
		log.info("Active count for : " + mapperName + " = " + list.size());
		return list.size();
	}
}
