package fspotcloud.server.control;

import java.util.logging.Logger;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.server.PropertyHandlerMapping;
import org.apache.xmlrpc.server.XmlRpcHandlerMapping;
import org.apache.xmlrpc.webserver.XmlRpcServlet;

import com.google.inject.Inject;
import com.google.inject.Singleton;

@SuppressWarnings("serial")
@Singleton
public class GuiceXmlRpcServlet extends XmlRpcServlet {
	private static final Logger log = Logger.getLogger(GuiceXmlRpcServlet.class
			.getName());

	@Inject
	private GuiceRequestProcessorFactoryFactory guiceFactoryFactory;

	
	/* (non-Javadoc)
	 * @see org.apache.xmlrpc.webserver.XmlRpcServlet#init(javax.servlet.ServletConfig)
	 */
	@Override
	public void init(ServletConfig pConfig) throws ServletException {
		log.info("Init called");
		setRequestProcessorFactoryFactory(guiceFactoryFactory);
		super.init(pConfig);
	}


//	@Override
//	protected XmlRpcHandlerMapping newXmlRpcHandlerMapping()
//			throws XmlRpcException {
//		log.info("Called");
//		PropertyHandlerMapping phm = new PropertyHandlerMapping();
//		phm.addHandler("Controller", Controller.class);
//		phm.setRequestProcessorFactoryFactory(guiceFactoryFactory);
//		return phm;
//	}

}
