package fspotcloud.server.mapreduce;

import java.util.logging.Logger;
import org.apache.hadoop.io.NullWritable;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.tools.mapreduce.AppEngineMapper;
import com.google.appengine.tools.mapreduce.DatastoreMutationPool;

/**
 * @author Ikai Lan
 */

public class DeleteAllMapper extends
		AppEngineMapper<Key, Entity, NullWritable, NullWritable> {
	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(DeleteAllMapper.class
			.getName());

	@Override
	public void map(Key key, Entity value, Context context) {
		DatastoreMutationPool mutationPool = this.getAppEngineContext(context)
				.getMutationPool();
		mutationPool.delete(key);
	}
}
