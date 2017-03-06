package org.ex.javabrains.messenger.resources.beans;

import javax.ws.rs.QueryParam;

public class MessageFilterBeans {
	/*
	 * here we are annotating member variables rather then annotating function arguments as 
	 * we did in MessageResource
	 */
	private @QueryParam("year")int yr;
	private @QueryParam("start")int start;
	private @QueryParam("size")int size;
	public int getYr() {
		return yr;
	}
	public void setYr(int yr) {
		this.yr = yr;
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}  
}
