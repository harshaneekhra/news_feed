package com.fetch.fbapp3.model;

public class likes {

	private int likeID;
	private int lstatusID;
	private int luserID;
	private int lflag;
	
	public likes(){
		
	}
	public likes(int likeID,int statusID,int userID,int flag)
	{
		this.likeID=likeID;
		this.lstatusID=statusID;
		this.luserID=userID;	
		this.lflag=flag;
	}

	public int getLikeID() {
		return likeID;
	}

	public void setLikeID(int likeID) {
		this.likeID = likeID;
	}

	public int getLstatusID() {
		return lstatusID;
	}

	public void setLstatusID(int lstatusID) {
		this.lstatusID = lstatusID;
	}

	public int getLuserID() {
		return luserID;
	}

	public void setLuserID(int luserID) {
		this.luserID = luserID;
	}

	public int getLflag() {
		return lflag;
	}

	public void setLflag(int lflag) {
		this.lflag = lflag;
	}
	
	
	
}
