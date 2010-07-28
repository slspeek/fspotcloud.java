package fspotcloud.server.control;

import java.util.logging.Logger;

import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.XmlRpcRequest;
import org.apache.xmlrpc.server.RequestProcessorFactoryFactory;

import com.google.inject.Inject;
import com.google.inject.Injector;

public class GuiceRequestProcessorFactoryFactory implements
		RequestProcessorFactoryFactory {

	private static final Logger log = Logger.getLogger(GuiceRequestProcessorFactoryFactory.class
			.getName());
	
//	private MetaReciever metaReciever;
//	@Inject
//	private TagReciever tagReciever;
//	@Inject
//	private PhotoReciever photoReciever;
	
	@Inject
	private Injector injector;
	@Inject
	public GuiceRequestProcessorFactoryFactory(Injector injector) {
		this.injector = injector;
	}
	
	@Override
	public RequestProcessorFactory getRequestProcessorFactory(final Class pClass)
			throws XmlRpcException {
		return new RequestProcessorFactory() {
			@SuppressWarnings("unchecked")
			@Override
			public Object getRequestProcessor(XmlRpcRequest pRequest)
					throws XmlRpcException {
				// TODO Auto-generated method stub
				log.info("Called for " + pClass.getName());
				return injector.getInstance(pClass);
				//return controller;
			}
		};
	}
}
