package fspotcloud.shared.photo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.SortedSet;

public class PhotoInfoStore implements Serializable {

	private static final long serialVersionUID = 4509115035183737104L;

	private List<PhotoInfo> store;
	
	@SuppressWarnings("unused")
	private PhotoInfoStore() {
		
	}
	
	public PhotoInfoStore(List<PhotoInfo> data) {
		this.store = data;
	}
	
	public PhotoInfoStore(SortedSet<PhotoInfo> set) {
		this(new ArrayList<PhotoInfo>(set));
	}
	
	public PhotoInfo getInfo(String id) {
		for (PhotoInfo pi: store) {
			if (id.equals(pi.getId())) {
				return pi;
			}
		}
		return null;
	}
	
	public PhotoInfo get(int index) {
		return store.get(index);
	}
	
	public int size() {
		return store.size();
	}
	
	public int indexOf(String id) {
		int index = -1;
		ListIterator<PhotoInfo> it = store.listIterator();
		while (it.hasNext()) {
			PhotoInfo pi = it.next();
			
			if (id.equals(pi.getId())) {
				index = it.previousIndex();
				break;
			}
		}
		return index;
	}
	
	public boolean isEmpty() {
		return store.isEmpty();
	}
	
	public PhotoInfo last() {
		return store.get(lastIndex());
	}
	
	public int lastIndex() {
		return store.size() - 1;
	}
	
	public String toString() {
		return store.toString();
	}
	
}
