package fspotcloud.server.admin.task;

import java.io.Serializable;
import java.util.concurrent.Callable;
import java.util.logging.Logger;

import com.google.inject.Inject;

import fspotcloud.server.model.api.Batch;
import fspotcloud.server.model.api.Batches;

public class FooTask implements Callable<Integer>, Serializable {
	private static final Logger log = Logger
	.getLogger(FooTask.class.getName());
	
	@Inject
	transient private Batches batches = null;
	
	
	public FooTask() {
		
	}
	
	@Override
	public Integer call() throws Exception {
		log.warning("FooTask called and AECS modified");
		Batch batch = batches.create("After deserialization 42");
		batches.save(batch);
		return 42;
	}

	

}
