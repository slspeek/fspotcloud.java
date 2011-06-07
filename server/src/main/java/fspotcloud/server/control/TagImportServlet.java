package fspotcloud.server.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import fspotcloud.server.model.api.PeerDatabases;

@SuppressWarnings("serial")
@Singleton
public class TagImportServlet extends HttpServlet {

	@Inject private PeerDatabases defaultPeer;
	@Inject private SchedulerInterface scheduler;
	
	@SuppressWarnings("unchecked")
	@Override
	public void service(ServletRequest request, ServletResponse response)
			throws ServletException, IOException {
		scheduler.schedule("sendTagData", Collections.EMPTY_LIST);
		PrintWriter out = response.getWriter();
		out.println("Import Tags was scheduled. -- " + defaultPeer.get().getName());
		out.close();
	}

}
