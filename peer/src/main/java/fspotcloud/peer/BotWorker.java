package fspotcloud.peer;

import java.awt.Dimension;
import java.net.URL;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;

import com.google.inject.Inject;

import fspotcloud.peer.db.Data;

public class BotWorker {

	final static private Logger log = Logger.getLogger(BotWorker.class.getName());
	
	final private XmlRpcClient controller;
	final private Data data;
	final private ImageData imageData;

	@Inject
	public BotWorker(XmlRpcClient controller, Data data, ImageData imageData) {
		this.controller = controller;
		this.data = data;
		this.imageData = imageData;
	}

	public int sendTagData() {
		int result = 0;
		try {
			Object[] tags = data.getTagList();
			Object[] args = new Object[] { tags };
			controller.execute("TagReciever.recieveTagData", args);
		} catch (SQLException e) {
			log.log(Level.SEVERE, "sendTagData threw (SQL) : ",e);
			result = 1;
		} catch (XmlRpcException e) {
			log.log(Level.SEVERE, "sendTagData threw (XmlRpc) : ",e);
			result = 2;
		}
		return result;
	}

	public int sendMetaData() {
		int result = 0;
		try {
			int count = data.getPhotoCount();
			Object[] args = new Object[] { count };
			controller.execute("MetaReciever.recieveMetaData", args);
		} catch (SQLException e) {
			log.log(Level.SEVERE, "sendMetaData threw (SQL) : ",e);
			result = 1;
		} catch (XmlRpcException e) {
			log.log(Level.SEVERE, "sendMetaData threw (XmlRpc) : ",e);
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
			log.log(Level.SEVERE, "sendPhotoData threw (XmlRpc) : ",e);
			result = 1;
		} catch (XmlRpcException e) {
			log.log(Level.SEVERE, "sendPhotoData threw (XmlRpc) : ",e);
			result = 2;
		}
		return result;
	}

	public int sendImageData(String photoId, String width, String height, String imageType) {
		try {
			URL url = data.getImageURL(photoId);
			Dimension size = new Dimension(Integer.valueOf(width), Integer
					.valueOf(height));
			byte[] data = imageData.getScaledImageData(url, size);
			Object[] params = new Object[] { photoId, data, Integer.valueOf(imageType) };
			controller.execute("PhotoReciever.recieveImageData", params);
		} catch (Exception e) {
			log.log(Level.SEVERE, "sendImageData threw: ",e);
		}
		return 0;
	}
	
}
