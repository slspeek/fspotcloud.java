package fspotcloud.peer;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.xmlrpc.XmlRpcException;

import com.google.inject.Inject;

import fspotcloud.peer.db.Data;

public class BotWorker {

	final static private Logger log = Logger.getLogger(BotWorker.class.getName());
	
	final private RemoteExecutor controller;
	final private Data data;

	@Inject
	public BotWorker(RemoteExecutor controller, Data data) {
		this.controller = controller;
		this.data = data;
	}

	public int sendTagData() {
		int result = 0;
		try {
			Object[] tags = data.getTagData();
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
	
	public int sendTagData(String offset, String limit) {
		int result = 0;
		try {
			Object[] tags = data.getTagData(offset, limit);
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
			int count = data.getCount("photos");
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
			Object[] photos = data.getPhotoData(offset, limit);
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
			Object imageBytes = data.getImageData(photoId, width, height, imageType);
			Object[] params = new Object[] { photoId, imageBytes, Integer.valueOf(imageType) };
			controller.execute("PhotoReciever.recieveImageData", params);
		} catch (Exception e) {
			log.log(Level.SEVERE, "sendImageData threw: ",e);
		}
		return 0;
	}
	
}
