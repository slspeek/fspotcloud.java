package fspotcloud.server.inject;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import com.google.inject.Guice;
import com.google.inject.Injector;

import fspotcloud.botdispatch.controller.dispatch.ControllerDispatchAsync;
import fspotcloud.server.model.api.PeerDatabase;
import fspotcloud.server.model.api.PeerDatabases;

public class InjectorTest {

     @Test
    public void testInjector() {
        Injector injector = Guice.createInjector(new J2eeTotalModule());
        AssertJUnit.assertNotNull(injector);
        PeerDatabases defaultPeer =
                injector.getInstance(PeerDatabases.class);
        PeerDatabase pd = defaultPeer.get();
        ControllerDispatchAsync controller = injector.getInstance(ControllerDispatchAsync.class);
        AssertJUnit.assertNotNull(controller);
    }
}
