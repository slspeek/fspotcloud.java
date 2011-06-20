package fspotcloud.client.place;

public class TokenizerUtil {

	final private String token;

	public TokenizerUtil(String token) {
		this.token = token;
	}
	
	public String getTagId() {
		String[] tokens = token.split(":");
		return tokens[0];
	}
	
	public String getPhotoId() {
		String[] tokens = token.split(":");
		return tokens[1];
	}
	
	public int getColumnCount() {
		String[] tokens = token.split(":");
		return Integer.parseInt(tokens[2]);
	}
	
	public int getRowCount() {
		String[] tokens = token.split(":");
		return Integer.parseInt(tokens[3]);
	}
}
