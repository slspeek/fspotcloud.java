package com.googlecode.fspotcloud.peer;

import com.google.inject.Guice;
import com.google.inject.Injector;

import com.googlecode.botdispatch.bot.Bot;
import com.googlecode.botdispatch.bot.BotModule;
import com.googlecode.fspotcloud.peer.inject.PeerActionsModule;
import com.googlecode.fspotcloud.peer.inject.PeerModule;

public class Main {

    public static void main(String[] args) throws Exception {
        final String workDir = System.getProperty("user.dir");
        final String db = System.getProperty("db");
        final int stopPort =
                Integer.valueOf(System.getProperty("stop.port", "4444"));


        Injector injector = Guice.createInjector(
                new PeerModule(db, workDir, stopPort),
                new PeerActionsModule(), new BotModule());

        StopListener stopListener = injector.getInstance(StopListener.class);
        stopListener.start();

        Bot bot = injector.getInstance(Bot.class);
        bot.runForever();
    }
}
