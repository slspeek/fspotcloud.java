package com.googlecode.fspotcloud.client.place;

public class TokenizerUtil {

	final private String[]  tokens;

	public TokenizerUtil(String token) {
		tokens = token.split(":");
	}
	
	public String getTagId() {
		
		return tokens[0];
	}
	
	public String getPhotoId() {
		return tokens[1];
	}
	
	public int getColumnCount() {
		return Integer.parseInt(tokens[2]);
	}
	
	public int getRowCount() {
		return Integer.parseInt(tokens[3]);
	}
	
	public boolean isTreeVisible() {
		return Boolean.parseBoolean(tokens[4]);
	}
	
	public boolean isButtonsVisible() {
		return Boolean.parseBoolean(tokens[5]);
	}
}
