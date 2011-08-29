package fspotcloud.server.mapreduce;

import java.util.logging.Logger;

import com.google.appengine.api.taskqueue.TaskOptions;
import com.google.appengine.api.taskqueue.TaskOptions.Method;

public class MapReduceUtil {
	private static final Logger log = Logger.getLogger(MapReduceUtil.class
			.getName());

	public static TaskOptions buildStartJob(String jobName, String kind) {
		TaskOptions task = TaskOptions.Builder
				.withUrl("/mapreduce/command/start_job").method(Method.POST)
				.header("X-Requested-With", "XMLHttpRequest") // hack: we need
																// to fix
																// appengine-mapper
																// so we can
																// properly call
																// start_job
																// without need
																// to pretend to
																// be an
																// ajaxmethod
				.param("name", jobName);
		addJobParam(task,
				"mapreduce.mapper.inputformat.datastoreinputformat.entitykind",
				kind);
		return task;
	}

	private static void addJobParam(TaskOptions task, String paramName,
			String paramValue) {
		task.param("mapper_params." + paramName, paramValue);
	}
}
