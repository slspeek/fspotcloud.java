package com.googlecode.fspotcloud.client.main;

import java.util.Date;
import java.util.List;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSortedSet;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.googlecode.fspotcloud.rpc.TagServiceAsync;
import com.googlecode.fspotcloud.shared.photo.PhotoInfo;
import com.googlecode.fspotcloud.shared.photo.PhotoInfoStore;
import com.googlecode.fspotcloud.shared.tag.TagNode;


public class TagServiceAsyncTestImpl implements TagServiceAsync {

	private List<TagNode> tagTreeData;

	public TagServiceAsyncTestImpl() {
		initData();
	}
	
	private static TagNode createNode(String id, String name, int count) {
		TagNode node = new TagNode();
		node.setId(id);
		node.setTagName(name);
		node.setCount(count);
		return node;
	}
	
	public List<TagNode> initData() {
		TagNode root1 = createNode("1", "Friends", 10);
		PhotoInfo pi1 = new PhotoInfo("1", "Daniel", new Date(1));
		PhotoInfo pi2 = new PhotoInfo("2", "Aute", new Date(2));
		PhotoInfo pi3 = new PhotoInfo("3", "Jan", new Date(3));
		ImmutableSortedSet<PhotoInfo> photoList = ImmutableSortedSet.of(pi1, pi2, pi3);
		root1.setCachedPhotoList(new PhotoInfoStore(photoList));
		
		TagNode cats = createNode("2", "Cats", 10);
		pi1 = new PhotoInfo("4", "", new Date(1));
		pi2 = new PhotoInfo("5", "", new Date(2));
		pi3 = new PhotoInfo("6", "", new Date(3));
		photoList = ImmutableSortedSet.of(pi1, pi2, pi3);
		cats.setCachedPhotoList(new PhotoInfoStore(photoList));
		
		TagNode empty = createNode("2.5", "Empty", 10);
		TagNode root3 = createNode("3", "Languages", 10);
		pi1 = new PhotoInfo("7", "Latin", new Date(1));
		pi2 = new PhotoInfo("8", "Greek", new Date(2));
		pi3 = new PhotoInfo("9", "Lisp", new Date(3));
		PhotoInfo pi4 = new PhotoInfo("9", "Python", new Date(4));
		photoList = ImmutableSortedSet.of(pi1, pi2, pi3, pi4);
		root3.setCachedPhotoList(new PhotoInfoStore(photoList));
		
		TagNode felix = createNode("4", "Felix", 2);
		pi1 = new PhotoInfo("11", "Snowie", new Date(1));
		pi2 = new PhotoInfo("12", "Siepie", new Date(2));
		pi3 = new PhotoInfo("13", "Pluk", new Date(3));
		photoList = ImmutableSortedSet.of(pi1, pi2, pi3);
		felix.setCachedPhotoList(new PhotoInfoStore(photoList));	
		
		TagNode woefje = createNode("5", "Woefje", 1);
		pi1 = new PhotoInfo("21", "", new Date(1));
		pi2 = new PhotoInfo("22", "", new Date(2));
		pi3 = new PhotoInfo("23", "", new Date(3000)); //Latest picture
		photoList = ImmutableSortedSet.of(pi1, pi2, pi3);
		woefje.setCachedPhotoList(new PhotoInfoStore(photoList));
		
		TagNode otherCats = createNode("6", "Other cats", 1);
		pi1 = new PhotoInfo("100", "", new Date(100));
		pi2 = new PhotoInfo("101", "", new Date(101));
		pi3 = new PhotoInfo("102", "", new Date(102));
		pi4 = new PhotoInfo("103", "", new Date(103));
		PhotoInfo pi5 = new PhotoInfo("104", "", new Date(104));
		PhotoInfo pi6 = new PhotoInfo("105", "", new Date(105));
		PhotoInfo pi7 = new PhotoInfo("106", "", new Date(106));
		PhotoInfo pi8 = new PhotoInfo("107", "", new Date(107));
		PhotoInfo pi9 = new PhotoInfo("108", "", new Date(108));
		photoList = ImmutableSortedSet.of(pi1, pi2, pi3, pi4, pi5, pi6, pi7, pi8, pi9);
		otherCats.setCachedPhotoList(new PhotoInfoStore(photoList));
		
		cats.addChild(empty);
		cats.addChild(felix);
		cats.addChild(woefje);
		cats.addChild(otherCats);
		tagTreeData = ImmutableList.of(root1, cats, root3);
		//System.out.println(felix.getCachedPhotoList());
		return tagTreeData;
	}
	
	@Override
	public void keysForTag(String tagId, AsyncCallback<List<String>> callback) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void loadTagTree(AsyncCallback<List<TagNode>> callback) {
		callback.onSuccess(tagTreeData);
	}
	
	@Override
	public void loadAdminTagTree(AsyncCallback<List<TagNode>> callback) {
		callback.onSuccess(tagTreeData);
	}

}
