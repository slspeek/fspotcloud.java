package fspotcloud.grapher;

import com.google.inject.Guice;
import com.google.inject.Injector;

import fspotcloud.botdispatch.controller.inject.ControllerServletModule;
import fspotcloud.botdispatch.model.MinimalCommandModelModule;
import fspotcloud.peer.inject.PeerActionsModule;
import fspotcloud.peer.inject.PeerModule;
import fspotcloud.server.control.task.TaskModule;
import fspotcloud.server.inject.ServerControllerModule;
import fspotcloud.server.inject.ServerModule;
import fspotcloud.server.model.ModelModule;

public class Main {

    Injector serverInjector = Guice.createInjector(new ServerModule(),
            new ModelModule(), new fspotcloud.server.inject.AdminActionsModule(),
            new TaskModule(), new ServerControllerModule(),
            new ControllerServletModule(), new MinimalCommandModelModule());
    Injector modelInjector = Guice.createInjector(new ModelModule());
    Injector peerInjector = Guice.createInjector(new PeerModule(), new PeerActionsModule());

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
