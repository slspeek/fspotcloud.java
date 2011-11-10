package fspotcloud.server.control.task.photoimport;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.name.Named;

@SuppressWarnings("serial")
@Singleton
public class PhotoImportTaskServlet extends HttpServlet {

	@Inject @Named("default") 
	PhotoImportScheduler scheduler;
	
	@Override
	public void service(ServletRequest request, ServletResponse response)
			throws ServletException, IOException {
		String startParam = request.getParameter("offset");
		String countParam = request.getParameter("limit");
		String minKey = request.getParameter("minKey");
		String tagId = request.getParameter("tagId");
		int start = Integer.valueOf(startParam);
		int count = Integer.valueOf(countParam);
		
		scheduler.schedulePhotoImport(tagId, minKey, start, count);
		
		PrintWriter out = response.getWriter();
		out.println("DataTask ran.");
		out.close();
	}
}
