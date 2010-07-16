package fspotcloud.server.control;

import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.server.PropertyHandlerMapping;
import org.apache.xmlrpc.server.XmlRpcHandlerMapping;
import org.apache.xmlrpc.webserver.XmlRpcServlet;

import com.google.inject.Inject;

public class GuiceXmlRpcServlet extends XmlRpcServlet {

	@Inject
	private GuiceRequestProcessorFactoryFactory guiceFactoryFactory;
	@Override
	protected XmlRpcHandlerMapping newXmlRpcHandlerMapping()
			throws XmlRpcException {
		
		 PropertyHandlerMapping phm = new PropertyHandlerMapping();
		 phm.addHandler("Controller", Controller.class);
		 phm.setRequestProcessorFactoryFactory(guiceFactoryFactory);
		return phm;
	}
	
	
	

}
