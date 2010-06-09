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
		List tagList = new ArrayList();
		ResultSet rs = stmt
				.executeQuery("SELECT id, name, category_id FROM tags ORDER BY id");
		while (rs.next()) {
			String tagId = rs.getString(1);
			String tagName = rs.getString(2);
			String desc = rs.getString(3);
			String photoCount = String.valueOf(getPhotoCountForTag(Integer
					.valueOf(tagId)));
			tagList.add(new Object[] { tagId, tagName, desc, photoCount });
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
	}

}
