package fspotcloud.server.cron;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.customware.gwt.dispatch.server.Dispatch;
import net.customware.gwt.dispatch.shared.DispatchException;

import com.google.inject.Inject;

import fspotcloud.shared.dashboard.actions.CountPhotos;
import fspotcloud.shared.dashboard.actions.ImportImageData;
import fspotcloud.shared.dashboard.actions.SynchronizePeer;

public class Cron {

	final private Dispatch dispatch;

	@Inject
	public Cron(Dispatch dispatch) {
		super();
		this.dispatch = dispatch;
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
			String action = request.getParameter("action");
			if (action.equals("import-image-data")) {
				dispatch.execute(new ImportImageData());
			} else if (action.equals("count-photos")) {
				dispatch.execute(new CountPhotos());
			} else if (action.equals("synchronize-peer")) {
				dispatch.execute(new SynchronizePeer());
			}
		} catch (DispatchException e) {
			response.getOutputStream().println(e.getMessage());
		}
	}

}
