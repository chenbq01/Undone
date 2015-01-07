package com.beiwaiclass.weixin.config;

public enum MediaType {

	IMAGE("image"), VOICE("voice"), VIDEO("video"), THUMB("thumb");

	private String stringValue;

	private MediaType(String stringValue) {
		this.stringValue = stringValue;
	}

	public String getStringValue() {
		return stringValue;
	}

}
