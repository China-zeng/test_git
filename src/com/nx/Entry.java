package com.nx;

public class Entry {
	private String key;
	private byte[] value;
	
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public byte[] getValue() {
		return value;
	}
	public void setValue(byte[] value) {
		this.value = value;
	}
	public Entry(String key, byte[] value) {
		super();
		this.key = key;
		this.value = value;
	}
	public Entry() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
