package fspotcloud.server.mapreduce;

import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.appengine.api.taskqueue.TaskOptions;
import com.google.appengine.api.taskqueue.TaskOptions.Method;
import com.google.appengine.api.urlfetch.HTTPHeader;
import com.google.appengine.api.urlfetch.HTTPRequest;
import com.google.appengine.api.urlfetch.HTTPResponse;
import com.google.appengine.api.urlfetch.URLFetchService;
import com.google.appengine.api.urlfetch.URLFetchServiceFactory;

import fspotcloud.server.admin.AdminServiceImpl;

public class MapReduceUtil {
	private static final Logger log = Logger.getLogger(AdminServiceImpl.class
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

	public static String startJob(String jobName, String kind) {
		TaskOptions task = buildStartJob(jobName, kind);
		String url = task.getUrl();
		URLFetchService fetcher = URLFetchServiceFactory.getURLFetchService();
		HTTPResponse response = null;
		HTTPRequest request = null;
		String host = "localhost:8080";
		String port = "80";
		try {
			host = InetAddress.getLocalHost().getHostName();
			port = host.equals("localhost") ? "8080" : "80";
		} catch (UnknownHostException e1) {
			log.log(Level.SEVERE, "could not retrieve hostname", e1);
		}
		try {
			request = new HTTPRequest(new URL("http://" + host + ":" + port
					+ url));
			request.addHeader(new HTTPHeader("X-Requested-With",
					"XMLHttpRequest"));
			response = fetcher.fetch(request);
		} catch (Exception e) {
			log.log(Level.SEVERE, "startJob failed", e);
		}
		String result = response != null ? new String(response.getContent())
				: "NO CONTENT";
		return result;
	}

	private static void addJobParam(TaskOptions task, String paramName,
			String paramValue) {
		task.param("mapper_params." + paramName, paramValue);
	}
}
