package fspotcloud.botdispatch.bot;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import junit.framework.TestCase;
import fspotcloud.botdispatch.test.TestAsyncCallback;

public class AsyncSerializationTest extends TestCase {

	
	public void testOne() throws IOException, ClassNotFoundException {
		TestAsyncCallback callback = new TestAsyncCallback();
		// Serialize to a byte array
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ObjectOutputStream out = new ObjectOutputStream(bos);
		out.writeObject(callback);
		out.close();

		// Get the bytes of the serialized object
		byte[] serializedResult = bos.toByteArray();
		
		ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(
				serializedResult
				));
		TestAsyncCallback back = (TestAsyncCallback) in.readObject();
		in.close();
		assertNotNull(back);
	}
}
