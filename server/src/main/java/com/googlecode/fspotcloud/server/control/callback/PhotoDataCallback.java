package com.googlecode.fspotcloud.server.control.callback;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.googlecode.fspotcloud.server.model.api.*;
import com.googlecode.fspotcloud.shared.peer.rpc.actions.PhotoData;
import com.googlecode.fspotcloud.shared.peer.rpc.actions.PhotoDataResult;
import com.googlecode.fspotcloud.shared.photo.PhotoInfo;

import java.util.*;

public class PhotoDataCallback implements AsyncCallback<PhotoDataResult>,
		Serializable {

	private static final long serialVersionUID = 246810426240427570L;

	@Inject
	transient private Photos photoManager;
	@Inject
	private transient Tags tagManager;
	@Inject
	private transient PeerDatabases peerDatabases;

	public PhotoDataCallback(Photos photoManager, Tags tagManager,
			PeerDatabases peerDatabases) {
		super();
		this.photoManager = photoManager;
		this.tagManager = tagManager;
		this.peerDatabases = peerDatabases;
	}

	@Override
	public void onFailure(Throwable caught) {
	}

	@Override
	public void onSuccess(PhotoDataResult result) {
		List<Photo> photoList = new ArrayList<Photo>();
		for (PhotoData photoData : result.getPhotoDataList()) {
			Photo p = recievePhoto(photoData);
			photoList.add(p);
		}
		photoManager.saveAll(photoList);
		clearTreeCache();
	}

	private void clearTreeCache() {
		PeerDatabase peer = peerDatabases.get();
		if (peer.getCachedTagTree() != null) {
			peer.setCachedTagTree(null);
			peerDatabases.save(peer);
		}
	}

	private Photo recievePhoto(PhotoData photoData) {
		String keyName = photoData.getPhotoId();
		String desc = photoData.getDescription();
		Date date = photoData.getDate();
		List<String> tags = photoData.getTagList();
		byte[] imageData = photoData.getImageData();
		byte[] thumbData = photoData.getThumbData();

		Photo photo = photoManager.findOrNew(keyName);
		photo.setDescription(desc);
		photo.setDate(date);
		photo.setTagList(tags);
		photo.setImage(imageData);
		photo.setThumb(thumbData);
		photo.setImageLoaded(true);
		photo.setThumbLoaded(true);
		for (String tagId : tags) {
			Tag tag = tagManager.find(tagId);
			PhotoInfo updatedInfo = new PhotoInfo(photo.getId(), photo.getDescription(), photo
					.getDate(), photoData.getVersion());
			TreeSet<PhotoInfo> cachedPhotoList = tag.getCachedPhotoList();
			cachedPhotoList.remove(updatedInfo);
			cachedPhotoList.add(
					updatedInfo);
                        tag.setCachedPhotoList(cachedPhotoList);
			tagManager.save(tag);
		}
		return photo;
	}

}
