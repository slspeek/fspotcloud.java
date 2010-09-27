package fspotcloud.peer;

import java.net.URL;

import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;
import org.apache.xmlrpc.client.XmlRpcCommonsTransportFactory;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.name.Named;
import com.google.inject.name.Names;

import fspotcloud.peer.db.Data;

public class BotModule extends AbstractModule {

	protected void configure() {

	     /*
	      * This tells Guice that whenever it sees a dependency on a TransactionLog,
	      * it should satisfy the dependency using a DatabaseTransactionLog.
	      */
	    bind(Bot.class);
	    bind(Data.class);
	    bind(BotWorker.class);
	    bind(ImageData.class);
	     /*
	      * Similarly, this binding tells Guice that when CreditCardProcessor is used in
	      * a dependency, that should be satisfied with a PaypalCreditCardProcessor.
	      */
	    bind(Pauser.class).to(PauserImpl.class);
	    bind(CommandFetcher.class).to(CommandFetcherImpl.class);
	    bind(String.class).annotatedWith(Names.named("JDBC URL"))
        .toInstance("jdbc:sqlite:/home/steven/.gnome2/f-spot/photos.db"); 
	    bind(String.class).annotatedWith(Names.named("endpoint"))
        .toInstance("http://" + System.getProperty("endpoint") + "/xmlrpc"); 
	    //.toInstance("http://localhost:8080/xmlrpc");
	    
	  }

	@Provides
	XmlRpcClient provideXmlRpcClient(@Named("endpoint") String endpoint) throws Exception{
		// create configuration
		XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
		config.setServerURL(new URL(endpoint));
		config.setEnabledForExtensions(true);
		config.setConnectionTimeout(60 * 1000);
		config.setReplyTimeout(60 * 1000);
		XmlRpcClient controller = new XmlRpcClient();
		// use Commons HttpClient as transport
		controller.setTransportFactory(new XmlRpcCommonsTransportFactory(
				controller));
		// set configuration
		controller.setConfig(config);
		return controller;
	}
}
