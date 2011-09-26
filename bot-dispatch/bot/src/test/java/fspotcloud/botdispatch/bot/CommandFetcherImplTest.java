package fspotcloud.botdispatch.bot;

import junit.framework.TestCase;

import org.jmock.Expectations;
import org.jmock.Mockery;

public class CommandFetcherImplTest extends TestCase {

	CommandFetcher fetcher;
	RemoteExecutor remote;
	Mockery context;

	@Override
	protected void setUp() throws Exception {
		context = new Mockery();
		remote = context.mock(RemoteExecutor.class);
		super.setUp();
	}

	public void testCommandFetcherImpl() {
		fetcher = new CommandFetcherImpl(remote);
		assertNotNull(fetcher);
	}

	public void testGetCommand() throws Exception {
		testCommandFetcherImpl();
		context.checking(new Expectations() {
			{
				oneOf(remote).execute(with(any(String.class)),
						with(any(Object[].class)));
				will(returnValue(new Object[] {}));
			}
		});

		Object[] command = fetcher.getCommand();
		assertEquals(0, command.length);
	}

}
