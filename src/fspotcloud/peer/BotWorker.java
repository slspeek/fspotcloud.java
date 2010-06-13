package fspotcloud.peer;

import java.sql.SQLException;

import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;

import fspotcloud.peer.db.Data;

public class BotWorker {

	private XmlRpcClient controller;
	private Data data;

	public BotWorker(XmlRpcClient controller) {
		this.controller = controller;
		this.data = new Data();
	}

	public int sendTagData(int offset, int count) {
		int result = 0;
		try {
			Object[] tags = data.getTagList();
			controller.execute("TagReciever.receiveTags", tags);
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			result = 1;
		} catch (XmlRpcException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result = 2;
		}
		return result;
	}

}
