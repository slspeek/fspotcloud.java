package fspotcloud.client.main.ui;

import java.util.logging.Logger;

import org.jmock.Expectations;
import org.jmock.Mockery;

import fspotcloud.client.view.action.KeyDispatcher;
import junit.framework.TestCase;

public class ResourcesTest extends TestCase {

	private static final Logger log = Logger.getLogger(KeyDispatcher.class
			.getName());
	
	public void testOne() {
		Mockery context  = new Mockery();
		final Resources res = context.mock(Resources.class);
		context.checking(new Expectations() {
			{
				oneOf(res).playIcon();

			}
		});
		log.info(""+res.playIcon());
	}
}
