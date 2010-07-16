package fspotcloud.server.control;

import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.XmlRpcRequest;
import org.apache.xmlrpc.server.RequestProcessorFactoryFactory;

import com.google.inject.Inject;
import com.google.inject.Injector;

import fspotcloud.server.control.reciever.MetaReciever;
import fspotcloud.server.control.reciever.PhotoReciever;
import fspotcloud.server.control.reciever.TagReciever;

public class GuiceRequestProcessorFactoryFactory implements
		RequestProcessorFactoryFactory {

	@Inject
	private Controller controller;
	@Inject
	private MetaReciever metaReciever;
	@Inject
	private TagReciever tagReciever;
	@Inject
	private PhotoReciever photoReciever;
	
	@Inject
	private Injector injector;
	
	@Override
	public RequestProcessorFactory getRequestProcessorFactory(final Class pClass)
			throws XmlRpcException {
		return new RequestProcessorFactory() {
			@Override
			public Object getRequestProcessor(XmlRpcRequest pRequest)
					throws XmlRpcException {
				// TODO Auto-generated method stub
				return injector.getInstance(pClass);
			}
		};
	}
}
