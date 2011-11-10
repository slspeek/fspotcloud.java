package fspotcloud.peer.db;

import java.awt.Dimension;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import com.google.inject.Inject;
import com.google.inject.name.Named;

import fspotcloud.peer.ImageData;
import fspotcloud.shared.peer.rpc.actions.PhotoData;
import fspotcloud.shared.peer.rpc.actions.TagData;

public class Data {

	final static private Logger log = Logger.getLogger(Data.class.getName());

	static {
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private final String jdbcURL;
	private final String photoDirectoryOverride;
	private final String photoDirectoryOriginalPath;
	private final ImageData imageData = new ImageData();

	@Inject
	public Data(@Named("JDBC URL") String jdbcURL) {
		this.jdbcURL = jdbcURL;
		this.photoDirectoryOverride = System.getProperty("photo.dir.override");
		this.photoDirectoryOriginalPath = System
				.getProperty("photo.dir.original");
	}

	private Connection getConnection() throws SQLException {
		Connection conn = DriverManager.getConnection(jdbcURL);
		return conn;
	}

	public int getCount(String kind) throws SQLException {
		Connection conn = getConnection();
		Statement stmt = conn.createStatement();
		int result;
		ResultSet rs = stmt.executeQuery("SELECT count(id) FROM " + kind);
		if (rs.next()) {
			result = rs.getInt(1);
		} else {
			throw new SQLException("Result for count query was empty");
		}
		return result;
	}

	public Object[] getMetaData() throws SQLException {
		return new Object[] { getCount("photos"), getCount("tags") };
	}

	public List<TagData> getTagData(int offset, int limit) throws SQLException {
		Connection conn = getConnection();
		Statement stmt = conn.createStatement();
		List<TagData> tagList = new ArrayList<TagData>();
		ResultSet rs = stmt
				.executeQuery("SELECT id, name, category_id FROM tags ORDER BY id LIMIT "
						+ limit + " OFFSET " + offset);
		while (rs.next()) {
			String tagId = rs.getString(1);
			String tagName = rs.getString(2);
			String parentId = rs.getString(3);
			int photoCount = getPhotoCountForTag(Integer.valueOf(tagId));
			tagList.add(new TagData(tagId, tagName, parentId, photoCount));
		}
		rs.close();
		conn.close();
		return tagList;
	}

	public List<PhotoData> getPhotoData(String tagId, int offset, int count,
			int w, int h, int tw, int th) throws Exception {
		List<PhotoData> result = new ArrayList<PhotoData>();
		Connection conn = getConnection();
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT id, description, time "
				+ "FROM photos, photo_tags WHERE photos.id=photo_tags.photo_id AND photo_tags.tag_id=\"" + tagId + "\"  ORDER BY id LIMIT " + count + " OFFSET "
				+ offset);
		while (rs.next()) {
			String id = rs.getString(1);
			String desc = rs.getString(2);
			long time = rs.getLong(3);
			Date date = new Date();
			date.setTime(time * 1000);
			List<String> tagList = getTagsForPhoto(Integer.valueOf(id));
			URL url = getImageURL(id);
			byte[] image = imageData.getScaledImageData(url, new Dimension(w,h));
			byte[] thumb = imageData.getScaledImageData(url, new Dimension(tw, th));
			result.add(new PhotoData(id, desc, date, image, thumb,tagList));
		}
		rs.close();
		conn.close();
		return result;
	}

	public Object[] getImageData(String photoId, String width, String height,
			String imageType) throws Exception {
		URL url = getImageURL(photoId);
		Dimension size = new Dimension(Integer.valueOf(width),
				Integer.valueOf(height));
		byte[] imageBytes = imageData.getScaledImageData(url, size);
		String exif = imageData.getExifData(getImageURL(photoId));
		// log.info("Exif: " + exif);
		Object[] params = new Object[] { photoId, exif, imageBytes,
				Integer.valueOf(imageType) };
		return params;
	}

	public Object[] getImageData(String photoId, int width, int height, int type)
			throws Exception {
		URL url = getImageURL(photoId);
		Dimension size = new Dimension(Integer.valueOf(width),
				Integer.valueOf(height));
		byte[] imageBytes = imageData.getScaledImageData(url, size);
		String exif = imageData.getExifData(getImageURL(photoId));
		// log.info("Exif: " + exif);
		Object[] params = new Object[] { photoId, exif, imageBytes, type };
		return params;
	}

	public URL getImageURL(String photoId) throws SQLException,
			MalformedURLException {
		String url = null;
		Connection conn = getConnection();
		Statement stmt = conn.createStatement();
		String query = "SELECT default_version_id, base_uri, filename "
				+ "FROM photos WHERE id = " + photoId;
		ResultSet rs = stmt.executeQuery(query);
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
		rs.close();
		conn.close();
		if (photoDirectoryOverride != null) {
			url = url.replaceFirst(photoDirectoryOriginalPath,
					photoDirectoryOverride);
		}
		// log.info("URL-String: " + url);
		return new URL(url);
	}

	private List<String> getTagsForPhoto(int id) throws SQLException {
		Connection conn = getConnection();
		Statement stmt = conn.createStatement();
		List<String> tagList = new ArrayList<String>();
		ResultSet rs = stmt.executeQuery("SELECT tag_id, photo_id "
				+ "FROM photo_tags WHERE photo_id=" + String.valueOf(id));
		while (rs.next()) {
			String tagId = rs.getString(1);
			tagList.add(tagId);
		}
		rs.close();
		conn.close();

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
			conn.close();
			return count;
		}
		rs.close();
		conn.close();
		throw new IllegalStateException();
	}

}
