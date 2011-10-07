package fspotcloud.peer;

import com.google.inject.Guice;
import com.google.inject.Injector;

import fspotcloud.botdispatch.bot.Bot;
import fspotcloud.botdispatch.bot.BotModule;
import fspotcloud.peer.inject.ActionsModule;
import fspotcloud.peer.inject.PeerModule;

public class Main {
	public static void main(String[] args) throws Exception {
		Injector injector = Guice.createInjector(new PeerModule(), new ActionsModule(), new BotModule());

		StopListener stopListener = injector.getInstance(StopListener.class);
	    stopListener.start();
		
	    Bot bot = injector.getInstance(Bot.class); 
	    bot.runForever();
	}
}
