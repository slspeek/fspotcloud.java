package fspotcloud.shared.peer.rpc.actions;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import junit.framework.TestCase;

import com.google.common.collect.ImmutableList;

public class GetPhotoDataTest extends TestCase {

	private static final ImageSpecs SPECS = new ImageSpecs(1024,768, 512, 378);
	private static final List<String> keys = ImmutableList.of("1","2");
	
	GetPhotoData action = new GetPhotoData(SPECS, keys);

	public void testSerialize() throws Exception {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ObjectOutputStream out = new ObjectOutputStream(bos);
		out.writeObject(action);
		out.close();
	}

	
	
}
