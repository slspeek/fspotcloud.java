package fspotcloud.peer.inject;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.google.inject.name.Names;

import fspotcloud.peer.ImageData;
import fspotcloud.peer.db.Data;

public class PeerModule extends AbstractModule {

	protected void configure() {
		bind(Data.class).in(Singleton.class);
		bind(ImageData.class);
		bind(String.class).annotatedWith(Names.named("JDBC URL")).toInstance(
				"jdbc:sqlite:" + System.getProperty("db"));
		bind(Integer.class).annotatedWith(Names.named("stop port")).toInstance(
				Integer.valueOf(System.getProperty("stop.port", "4444")));
	}
}
