package fspotcloud.taskqueuedispatch;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;

import net.customware.gwt.dispatch.server.Dispatch;
import net.customware.gwt.dispatch.shared.DispatchException;
import net.customware.gwt.dispatch.shared.Result;

import org.apache.commons.lang.SerializationUtils;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Singleton;

@SuppressWarnings("serial")
@Singleton
public class TaskQueueDispatchServlet extends HttpServlet {

	@Inject Dispatch dispatch;
	@Inject Injector injector;
	
	@Override
	public void service(ServletRequest request, ServletResponse response)
			throws ServletException, IOException {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		InputStream in = request.getInputStream();
		BufferedInputStream buff = new BufferedInputStream(in);
		byte[] buf = new byte[4 * 1024];
		int len; 
		while ((len = buff.read(buf, 0, buf.length)) != -1) { 
			out.write(buf, 0, len); 
		} 
		in.close();
		buff.close();
		out.close();
		byte[] payload = out.toByteArray();
		AsyncCommand command = (AsyncCommand) SerializationUtils.deserialize(payload);
		AsyncCallback callback = command.getCallback();
		injector.injectMembers(callback);
		try {
			Result result = dispatch.execute(command.getAction());
			callback.onSuccess(result);
		} catch (DispatchException e) {
			callback.onFailure(e);
			throw new ServletException(e);
		}
		PrintWriter out2 = response.getWriter();
		out2.println("TaskQueue dispatch ran.");
		out2.close();
	}
}
