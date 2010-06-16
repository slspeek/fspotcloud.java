package fspotcloud.peer.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Data {

	static {
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private Connection getConnection() throws SQLException {
		Connection conn = DriverManager
				.getConnection("jdbc:sqlite:/home/steven/.gnome2/f-spot/photos.db");
		return conn;
	}

	public Object[] getTagList() throws SQLException {
		Connection conn = getConnection();
		Statement stmt = conn.createStatement();
		List<Object[]> tagList = new ArrayList<Object[]>();
		ResultSet rs = stmt
				.executeQuery("SELECT id, name, category_id FROM tags ORDER BY id");
		while (rs.next()) {
			String tagId = rs.getString(1);
			String tagName = rs.getString(2);
			String parentId = rs.getString(3);
			String photoCount = String.valueOf(getPhotoCountForTag(Integer
					.valueOf(tagId)));
			tagList.add(new Object[] { tagId, tagName, parentId, photoCount });
		}
		rs.close();
		conn.close();

		return tagList.toArray();
	}

	public Object[] getPhotoList(int offset, int limit) throws SQLException {
		Connection conn = getConnection();
		Statement stmt = conn.createStatement();
		List<Object[]> photoList = new ArrayList<Object[]>();
		ResultSet rs = stmt
				.executeQuery("SELECT id, name, category_id "
						+ "FROM tags ORDER BY id LIMIT "+ String.valueOf(limit)
						+ " OFFSET " + String.valueOf(offset));
		while (rs.next()) {
			String id = rs.getString(1);
			String desc = rs.getString(2);
			Object[] tagList = getTagsForPhoto(Integer.valueOf(id));
			
			
			photoList.add(new Object[] { id, desc, tagList });
		}
		rs.close();
		conn.close();

		return photoList.toArray();
	}
	
	private Object[] getTagsForPhoto(int id) throws SQLException {
		Connection conn = getConnection();
		Statement stmt = conn.createStatement();
		List<String> tagList = new ArrayList<String>();
		ResultSet rs = stmt
		.executeQuery("SELECT tag_id, photo_id " + 
				"FROM photo_tags WHERE photo_id=" + String.valueOf(id));
		while (rs.next()) {
			String tagId = rs.getString(1);
			tagList.add(tagId);
		}
		rs.close();
		conn.close();
		
		return tagList.toArray();
	}

	private int getPhotoCountForTag(int tagId) throws SQLException {
		Connection conn = getConnection();
		PreparedStatement prep = conn
				.prepareStatement("SELECT COUNT(photos.id) FROM photo_tags, photos WHERE tag_id=? AND photos.id=photo_tags.photo_id ");
		prep.setInt(1, tagId);
		ResultSet rs = prep.executeQuery();
		if (rs.next()) {
			int count = rs.getInt(1);
			rs.close();
			conn.close();
			return count;
		}
		rs.close();
		conn.close();
		throw new IllegalStateException();
	}

	public static void main(String[] args) throws Exception {
		Data d = new Data();
		Object[] list = d.getTagList();
		for (Object item: list) {
			Object[] itemArray = (Object[]) item;
			System.out.println(itemArray[1]);
		}
		list = d.getPhotoList(10,10);
		for (Object item: list) {
			Object[] itemArray = (Object[]) item;
			Object[] tagArray = (Object[]) itemArray[2];
			for (Object id: tagArray) {
				System.out.print(id + " ");
			}
			System.out.println(" id: " + itemArray[0]);
			
		}
		
	}

}
