package fspotcloud.peer;

import junit.framework.TestCase;

import com.google.inject.Guice;
import com.google.inject.Injector;

import fspotcloud.botdispatch.bot.Bot;
import fspotcloud.botdispatch.bot.BotModule;
import fspotcloud.peer.inject.PeerActionsModule;
import fspotcloud.peer.inject.PeerModule;

public class PeerModuleTest extends TestCase {

	public void testInjector() {
		Injector injector = Guice.createInjector(new PeerModule(), new PeerActionsModule(), new BotModule());
		assertNotNull(injector);
		 Bot bot = injector.getInstance(Bot.class); 
	}
}
