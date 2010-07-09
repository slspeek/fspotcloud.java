package fspotcloud.server.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;


public class TagImportServlet extends GenericServlet {

	@Override
	public void service(ServletRequest request, ServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		Scheduler.schedule("sendTagData", Collections.EMPTY_LIST);
		PrintWriter out = response.getWriter();

		out.println("Import Tags was scheduled.");
		out.close();
	}

}
