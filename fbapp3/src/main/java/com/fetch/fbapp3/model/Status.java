package com.fetch.fbapp3.model;

import java.util.ArrayList;
import java.util.Date;



public class Status {
	 private int statusID;
	   private String status_desc;
	   private String created;
	   private int author_id;
	   private int flag;  
	   private int likes_count;
	   /*private int likeID;
	   private int commentID;
	   private String comment_desc;*/
	   ArrayList<comment> a;
	   public Status(){
		 //  a=new ArrayList<comment>();
	   }
	   public Status(int statusID,String status_desc,int author_id,String created,int LikesCount){
		   this.statusID=statusID;
		   this.status_desc=status_desc;
		   this.author_id=author_id;
		   this.created=created;
		   flag=1;
		   this.likes_count=LikesCount;
		   a=new ArrayList<comment>();
		   }
	  
	public int getStatusID() {
		return statusID;
	}
	public void setStatusID(int statusID) {
		this.statusID = statusID;
	}
	public int getAuthor_id() {
		return author_id;
	}
	public void setAuthor_id(int author_id) {
		this.author_id = author_id;
	}
	
	
	

	public String getStatus_desc() {
		return status_desc;
	}
	public void setStatus_desc(String status_desc) {
		this.status_desc = status_desc;
	}
	public String getCreated() {
		return created;
	}
	public void setCreated(String created) {
		this.created = created;
	}
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	public ArrayList<comment> getA() {
		return a;
	}
	public void setA(ArrayList<comment> a) {
		System.out.println("****hereeee******");
		this.a = a;
	}
	public int getLikesCount() {
		return likes_count;
	}
	public void setLikesCount(int LikesCount) {
		
		this.likes_count = LikesCount;
		System.out.println("setting likes count"+likes_count);
	}
	
}