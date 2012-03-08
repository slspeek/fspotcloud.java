package com.googlecode.fspotcloud.server.inject;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.googlecode.botdispatch.controller.dispatch.ControllerDispatchAsync;
import com.googlecode.fspotcloud.server.model.api.PeerDatabases;
import org.testng.AssertJUnit;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class InjectorTest {

    @BeforeClass
    public void load() {
    }

    @Test
    public void testInjector() {
        Injector injector = Guice.createInjector(new GaeTotalModule());
        AssertJUnit.assertNotNull(injector);
        PeerDatabases defaultPeer =
                injector.getInstance(PeerDatabases.class);
        ControllerDispatchAsync controller = injector.getInstance(ControllerDispatchAsync.class);
        AssertJUnit.assertNotNull(controller);
    }
}
