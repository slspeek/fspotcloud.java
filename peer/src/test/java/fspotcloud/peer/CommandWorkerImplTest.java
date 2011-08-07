package fspotcloud.peer;

import junit.framework.TestCase;

import org.jmock.Expectations;
import org.jmock.Mockery;

public class CommandWorkerImplTest extends TestCase {

	Mockery context;
	CommandWorker worker;
	DataSender sender;
	DataFetcher fetcher;
	Object[] args = new Object[] {"0", "2"};
	Object[] wrappedArgs = new Object[] { args };
	
	protected void setUp() throws Exception {
		context = new Mockery();
		sender = context.mock(DataSender.class);
		fetcher = context.mock(DataFetcher.class);
	}
	
	public void testCommandWorkerImpl() {
		worker = new CommandWorkerImpl(sender, fetcher, "sendTagData", args);
		assertNotNull(worker);
	}

	public void testRun() throws Exception {
		testCommandWorkerImpl();
		context.checking(new Expectations() {
			{
				oneOf(sender).sendData(with("TagReciever.recieveTagData"),
						with(any(Object[].class)));
				oneOf(fetcher).getData(with("getTagData"),
						with(args));
				
			}
		});
		worker.run();
	}

	public void testRunImageData() throws Exception {
		final String[] imageArgs = new String[]{"0", "1", "1"};
		final String[] imageReturn = new String[]{"0", "1", "3"};
		worker = new CommandWorkerImpl(sender, fetcher, "sendImageData", imageArgs);
		context.checking(new Expectations() {
			{
				oneOf(sender).sendData(with("PhotoReciever.recieveImageData"),
						with(imageReturn));
				oneOf(fetcher).getData(with("getImageData"),
						with(imageArgs));will(returnValue(imageReturn));
				
			}
		});
		worker.run();
	}

}
