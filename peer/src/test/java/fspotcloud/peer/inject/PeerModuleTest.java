package fspotcloud.peer.inject;

import junit.framework.TestCase;

import com.google.inject.Guice;
import com.google.inject.Injector;

import fspotcloud.botdispatch.bot.BotModule;
import fspotcloud.peer.db.Data;

public class PeerModuleTest extends TestCase {

	
	public void testOne() throws InterruptedException {
		Injector injector = Guice.createInjector(new PeerModule(),
				new PeerActionsModule(), new BotModule());
		Data data = injector.getInstance(Data.class);
	}
}
