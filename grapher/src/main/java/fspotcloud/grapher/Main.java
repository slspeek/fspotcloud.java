package fspotcloud.grapher;

import com.google.inject.Guice;
import com.google.inject.Injector;

import fspotcloud.peer.inject.BotModule;
import fspotcloud.server.control.task.TaskModule;
import fspotcloud.server.inject.FSpotCloudModule;
import fspotcloud.server.model.ModelModule;

public class Main {
	Injector serverInjector = Guice.createInjector(new FSpotCloudModule(),
			new ModelModule(), new TaskModule());
	Injector modelInjector = Guice.createInjector(new ModelModule());
	Injector peerInjector = Guice.createInjector(new BotModule());

	// Injector clientInjector = Guice.createInjector(new GinModuleAdapter(new
	// FakeForGrapherAppModule()));

	public static void main(String[] args) throws Exception {
		Main g = new Main();
		g.plotAll();

	}

	private void plotAll() throws Exception {
		plot(modelInjector, "model");
		plot(serverInjector, "server");
		plot(peerInjector, "peer");
	}

	private void plot(Injector injector, String name) throws Exception {
		new Grapher(injector, name);
	}
}
