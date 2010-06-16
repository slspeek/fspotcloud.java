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

	public int sendTagData() {
		int result = 0;
		try {
			Object[] tags = data.getTagList();
			Object[] args = new Object[] { tags };
			controller.execute("TagReciever.recieveTagData", args);
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

	public int sendPhotoData(String offset, String limit) {
		int result = 0;
		try {
			Object[] photos = data.getPhotoList(offset, limit);
			Object[] args = new Object[] { photos };
			controller.execute("PhotoReciever.recievePhotoData", args);
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
