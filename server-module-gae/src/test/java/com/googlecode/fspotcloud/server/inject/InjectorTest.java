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
        System.setProperty("appengine.orm.disable.duplicate.emf.exception", "true");
        Injector injector = Guice.createInjector(new GaeTotalModule(10, "FOO_SECRET"));
        AssertJUnit.assertNotNull(injector);
        PeerDatabases defaultPeer =
                injector.getInstance(PeerDatabases.class);
        ControllerDispatchAsync controller = injector.getInstance(ControllerDispatchAsync.class);
        AssertJUnit.assertNotNull(controller);
        System.clearProperty("appengine.orm.disable.duplicate.emf.exception");
    }
}
