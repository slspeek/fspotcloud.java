package fspotcloud.peer;

import junit.framework.TestCase;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class BotModuleTest extends TestCase {

	public void testInjector() {
		Injector injector = Guice.createInjector(new BotModule());
		assertNotNull(injector);
	}
}
