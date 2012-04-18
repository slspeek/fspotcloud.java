package com.googlecode.fspotcloud.peer.inject;

import junit.framework.TestCase;

import com.google.inject.Guice;
import com.google.inject.Injector;

import com.googlecode.botdispatch.bot.Bot;
import com.googlecode.botdispatch.bot.BotModule;

public class PeerModuleTest extends TestCase {

    public void testInjector() {
        final String workDir = System.getProperty("user.dir");
        final String db = System.getProperty(
                "db",
                System.getProperty("user.dir")
                + "/src/test/resources/photos.db");
        final int stopPort =
                Integer.valueOf(System.getProperty("stop.port", "4444"));

        Injector injector = Guice.createInjector(
                new PeerModule(db, workDir, stopPort),
                new PeerActionsModule(), new BotModule());
        assertNotNull(injector);
        Bot bot = injector.getInstance(Bot.class);
    }
}
