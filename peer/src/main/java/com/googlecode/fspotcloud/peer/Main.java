package com.googlecode.fspotcloud.peer;

import com.google.inject.Guice;
import com.google.inject.Injector;

import com.googlecode.botdispatch.bot.Bot;
import com.googlecode.botdispatch.bot.BotModule;
import com.googlecode.fspotcloud.peer.inject.PeerActionsModule;
import com.googlecode.fspotcloud.peer.inject.PeerModule;

public class Main {
	public static void main(String[] args) throws Exception {
		Injector injector = Guice.createInjector(new PeerModule(),
				new PeerActionsModule(), new BotModule());

		StopListener stopListener = injector.getInstance(StopListener.class);
		stopListener.start();

		Bot bot = injector.getInstance(Bot.class);
		bot.runForever();
	}
}
