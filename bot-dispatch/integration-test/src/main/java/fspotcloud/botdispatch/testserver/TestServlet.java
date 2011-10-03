package fspotcloud.botdispatch.testserver;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import fspotcloud.botdispatch.controller.dispatch.ControllerDispatchAsync;
import fspotcloud.botdispatch.model.api.Commands;
import fspotcloud.botdispatch.test.TestAction;

@SuppressWarnings("serial")
@Singleton
public class TestServlet extends HttpServlet {

	final static private Logger log = Logger.getLogger(TestServlet.class
			.getName());
	@Inject
	Commands commandManager;
	@Inject
	ControllerDispatchAsync dispatch;
	
	@Inject
	List results;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String name = request.getParameter("name");
		if (name != null) {
			dispatch.execute(new TestAction(name), new TestCallback());
		}
		log.info(outputHTML());
		OutputStream out = response.getOutputStream();
		PrintWriter p = new PrintWriter(out);
		p.write(outputHTML());
		p.close();
		out.close();
	}

	private String outputHTML() {
		String result = "<html><h1>Test Servlet</h1><div>";
		for (Object t : results) {
			result += "<span>" + String.valueOf(t) + "</span>";
		}
		result += "</div></html>";
		return result;
	}

}
