package fspotcloud.peer.db;

import java.awt.Dimension;
import java.net.MalformedURLException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.common.annotations.VisibleForTesting;
import com.google.inject.Inject;
import com.google.inject.name.Named;

import fspotcloud.peer.ImageData;
import fspotcloud.shared.peer.rpc.actions.ImageSpecs;
import fspotcloud.shared.peer.rpc.actions.PhotoData;
import fspotcloud.shared.peer.rpc.actions.TagData;

public class Data {

	final static private Logger log = Logger.getLogger(Data.class.getName());

	static {
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			log.log(Level.SEVERE, "Driver not found", e);
		}
	}
	private String jdbcURL;
	private final String photoDirectoryOverride;
	private final String photoDirectoryOriginalPath;
	private final ImageData imageData = new ImageData();
	private Connection connection;;

	@Inject
	public Data(@Named("JDBC URL") String jdbcURL) {
		this.jdbcURL = jdbcURL;
		this.photoDirectoryOverride = System.getProperty("photo.dir.override");
		this.photoDirectoryOriginalPath = System
				.getProperty("photo.dir.original");
	}

	@VisibleForTesting
	public void setJDBCUrl(String jdbcURL) throws SQLException {
		log.info("setting: " + jdbcURL);
		this.jdbcURL = jdbcURL;
		if (connection != null) {
			// connection.close();
			connection = null;
		}
	}

	private Connection getConnection() throws SQLException {
		if (connection == null) {
			connection = DriverManager.getConnection(jdbcURL);
		}
		return connection;
	}

	public int getCount(String kind) throws SQLException {
		Connection conn = null;
		int result;
		try {
			conn = getConnection();
			Statement stmt = conn.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT count(id) FROM " + kind);
			if (rs.next()) {
				result = rs.getInt(1);
			} else {
				throw new SQLException("Result for count query was empty");
			}
		} finally {

		}
		return result;
	}

	public Object[] getMetaData() throws SQLException {
		return new Object[] { getCount("photos"), getCount("tags") };
	}

	public List<TagData> getTagData(int offset, int limit) throws SQLException {
		Connection conn = null;
		ResultSet rs = null;
		List<TagData> tagList;
		try {
			conn = getConnection();
			Statement stmt = conn.createStatement();
			tagList = new ArrayList<TagData>();
			rs = stmt
					.executeQuery("SELECT id, name, category_id FROM tags ORDER BY id LIMIT "
							+ limit + " OFFSET " + offset);
			while (rs.next()) {
				String tagId = rs.getString(1);
				String tagName = rs.getString(2);
				String parentId = rs.getString(3);
				int photoCount = getPhotoCountForTag(Integer.valueOf(tagId));
				tagList.add(new TagData(tagId, tagName, parentId, photoCount));
			}
		} finally {
			rs.close();
		}
		return tagList;
	}

	public List<String> getPhotoKeysInTag(String tagId) throws Exception {
		List<String> result = new ArrayList<String>();
		Connection conn = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			Statement stmt = conn.createStatement();

			rs = stmt
					.executeQuery("SELECT id FROM photos, photo_tags WHERE photos.id=photo_tags.photo_id AND photo_tags.tag_id=\""
							+ tagId + "\"");
			while (rs.next()) {
				String id = rs.getString(1);
				result.add(id);
			}
		} finally {
			rs.close();
		}
		return result;
	}

	public String getImageURL(String photoId) throws SQLException,
			MalformedURLException {
		String url = null;
		Connection conn = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			Statement stmt = conn.createStatement();
			String query = "SELECT default_version_id, base_uri, filename "
					+ "FROM photos WHERE id = " + photoId;
			rs = stmt.executeQuery(query);
			if (rs.next()) {
				int version = rs.getInt(1);
				if (version == 1) {
					url = rs.getString(2) + "/" + rs.getString(3);
				} else {
					stmt = conn.createStatement();
					query = "SELECT base_uri, filename "
							+ "FROM photo_versions WHERE photo_id =" + photoId
							+ " AND version_id=" + version;
					rs = stmt.executeQuery(query);
					if (rs.next()) {
						url = rs.getString(1) + "/" + rs.getString(2);
					}
				}
			}
		} finally {
			rs.close();
		}
		if (photoDirectoryOverride != null) {
			url = url.replaceFirst(photoDirectoryOriginalPath,
					photoDirectoryOverride);
		}
		// log.info("URL-String: " + url);
		return url;
	}

	private List<String> getTagsForPhoto(int id) throws SQLException {
		Connection conn = null;
		ResultSet rs = null;
		List<String> tagList = new ArrayList<String>();
		try {
			conn = getConnection();
			Statement stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT tag_id, photo_id "
					+ "FROM photo_tags WHERE photo_id=" + String.valueOf(id));
			while (rs.next()) {
				String tagId = rs.getString(1);
				tagList.add(tagId);
			}
		} finally {
			rs.close();
		}
		return tagList;
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
			return count;
		}
		rs.close();
		throw new IllegalStateException();
	}

	public List<PhotoData> getPhotoData(ImageSpecs imageSpecs,
			List<String> imageKeys) throws SQLException {
		List<PhotoData> result = new ArrayList<PhotoData>();
		for (String imageKey : imageKeys) {
			Connection conn = null;
			ResultSet rs = null;
			try {
				conn = getConnection();
				Statement stmt = conn.createStatement();
				rs = stmt
						.executeQuery("SELECT id, description, time, default_version_id "
								+ "FROM photos WHERE id=\"" + imageKey + "\"");
				while (rs.next()) {
					String id = rs.getString(1);
					String desc = rs.getString(2);
					long time = rs.getLong(3);
					int version = rs.getInt(4);
					Date date = new Date();
					date.setTime(time * 1000);
					List<String> tagList = getTagsForPhoto(Integer.valueOf(id));
					String url = getImageURL(id);
					byte[] image = imageData.getScaledImageData(
							url,
							new Dimension(imageSpecs.getWidth(), imageSpecs
									.getHeight()));
					byte[] thumb = imageData.getScaledImageData(url,
							new Dimension(imageSpecs.getThumbWidth(),
									imageSpecs.getThumbHeight()));
					result.add(new PhotoData(id, desc, date, image, thumb,
							tagList, version));
				}
			} catch (Exception e) {
				log.log(Level.SEVERE, "getPhotoData: ", e);
			} finally {
				rs.close();
			}
		}
		return result;
	}

	public int getPhotoDefaultVersion(String photoId) throws SQLException {
		Connection conn = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			Statement stmt = conn.createStatement();
			rs = stmt
					.executeQuery("SELECT id, description, time, default_version_id "
							+ "FROM photos WHERE id=\"" + photoId + "\"");
			while (rs.next()) {
				int version = rs.getInt(4);
				return version;
			}
		} catch (Exception e) {
			log.log(Level.SEVERE, "getPhotoDefaultVersion: ", e);
		} finally {
			rs.close();

		}
		return -1;
	}

	public boolean isPhotoInTag(String tagId, String photoId)
			throws SQLException {
		boolean result = false;
		Connection conn = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			Statement stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT photo_id, tag_id "
					+ "FROM photo_tags WHERE photo_id=\"" + photoId
					+ "\" AND tag_id=\"" + tagId + "\"");
			if (rs.next()) {
				result = true;
			}
		} catch (Exception e) {
			log.log(Level.SEVERE, "isPhotoInTag: ", e);
		} finally {
			rs.close();
		}
		return result;
	}

}
