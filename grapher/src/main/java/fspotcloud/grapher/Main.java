package fspotcloud.grapher;

import com.google.inject.Guice;
import com.google.inject.Injector;

import fspotcloud.peer.inject.PeerActionsModule;
import fspotcloud.peer.inject.PeerModule;
import fspotcloud.server.inject.J2eeTotalModule;
import fspotcloud.server.model.ModelModule;

public class Main {

    //Injector serverGaeInjector = Guice.createInjector(new GaeTotalModule());
    Injector serverJ2eeInjector = Guice.createInjector(new J2eeTotalModule());
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
        //plot(serverGaeInjector, "server-gae");
        plot(serverJ2eeInjector, "server-j2ee");
        plot(peerInjector, "peer");
    }

    private void plot(Injector injector, String name) throws Exception {
        new Grapher(injector, name);
    }
}
