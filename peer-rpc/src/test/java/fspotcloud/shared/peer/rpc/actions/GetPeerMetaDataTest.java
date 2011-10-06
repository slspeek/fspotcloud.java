package fspotcloud.shared.peer.rpc.actions;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;

import junit.framework.TestCase;

public class GetPeerMetaDataTest extends TestCase {


	GetPeerMetaData action = new GetPeerMetaData();
	
	public void testSerialize() throws Exception {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ObjectOutputStream out = new ObjectOutputStream(bos);
		out.writeObject(action);
		out.close();
	}
}
