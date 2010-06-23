package fspotcloud.peer;

import java.awt.Dimension;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;

import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;

import fspotcloud.peer.db.Data;

public class BotWorker {

	private XmlRpcClient controller;
	private Data data;
	private ImageData imageData;

	public BotWorker(XmlRpcClient controller) {
		this.controller = controller;
		this.data = new Data();
		this.imageData = new ImageData();
	}

	public int sendTagData() {
		int result = 0;
		try {
			Object[] tags = data.getTagList();
			Object[] args = new Object[] { tags };
			controller.execute("TagReciever.recieveTagData", args);
		} catch (SQLException e) {
			e.printStackTrace();
			result = 1;
		} catch (XmlRpcException e) {
			e.printStackTrace();
			result = 2;
		}
		return result;
	}

	public int sendPhotoCount() {
		int result = 0;
		try {
			int count = data.getPhotoCount();
			Object[] args = new Object[] { count };
			controller.execute("TagReciever.recieveMetaData", args);
		} catch (SQLException e) {
			e.printStackTrace();
			result = 1;
		} catch (XmlRpcException e) {
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
			e.printStackTrace();
			result = 1;
		} catch (XmlRpcException e) {
			e.printStackTrace();
			result = 2;
		}
		return result;
	}
	
	public int sendImageData(String photoId, String width, String height) {
		try {
			URL url = data.getImageURL(photoId);
			Dimension size = new Dimension(Integer.valueOf(width),
					Integer.valueOf(height));
			byte[] data = imageData.getScaledImageData(url, size);
			Object[] params = new Object[] { photoId, data };
			controller.execute("PhotoReciever.recieveImageData", params);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XmlRpcException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
	}
}
