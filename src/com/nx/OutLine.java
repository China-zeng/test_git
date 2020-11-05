package com.nx;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OutLine {
	private String title;
	private String page;
	private String action;
	private String open;
	private List<OutLine> kids;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPage() {
		return page;
	}
	public void setPage(String page) {
		this.page = page;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getOpen() {
		return open;
	}
	public void setOpen(String open) {
		this.open = open;
	}
	public List<OutLine> getKids() {
		return kids;
	}
	public void setKids(List<OutLine> kids) {
		this.kids = kids;
	}
	public Map getMap(){
		HashMap hm = new HashMap();
		hm.put("Title", title);
		hm.put("Page", page);
		hm.put("Action", action);
		return hm;
	}
}
