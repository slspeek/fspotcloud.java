package fspotcloud.peer;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class Main {
	public static void main(String[] args) throws Exception {
		Injector injector = Guice.createInjector(new BotModule());

	    /*
	     * Now that we've got the injector, we can build objects.
	     */
		StopListener stopListener = injector.getInstance(StopListener.class);
	    stopListener.start();
		
	    Bot bot = injector.getInstance(Bot.class); 
	    bot.serveForever();
	}
}
