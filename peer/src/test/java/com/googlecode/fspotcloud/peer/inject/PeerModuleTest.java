package com.googlecode.fspotcloud.peer.inject;

import com.googlecode.fspotcloud.peer.inject.PeerActionsModule;
import com.googlecode.fspotcloud.peer.inject.PeerModule;
import junit.framework.TestCase;

import com.google.inject.Guice;
import com.google.inject.Injector;

import com.googlecode.botdispatch.bot.BotModule;
import com.googlecode.fspotcloud.peer.db.Data;

public class PeerModuleTest extends TestCase {

	
	public void testOne() throws InterruptedException {
		Injector injector = Guice.createInjector(new PeerModule(),
				new PeerActionsModule(), new BotModule());
		Data data = injector.getInstance(Data.class);
	}
}
