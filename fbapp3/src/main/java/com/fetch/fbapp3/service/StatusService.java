package com.fetch.fbapp3.service;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.fetch.fbapp3.model.Status;
import com.google.gson.JsonArray;
import com.mysql.jdbc.ResultSetMetaData;


public class StatusService {
	 DBAccess db= new DBAccess();
	 public ArrayList<Status> getStatusByUser(int user_id){
        String result;
		boolean check=false;
		try{
		  while(check!=true){
			  System.out.println("trying connection in getStatusByUser");
			 check= db.start();
		  }
		  String sql="select * from status where user_id= ?";
		 
		  PreparedStatement pstmnt=db.con.prepareStatement(sql);
		  pstmnt.setInt(1,user_id); // user_id is the one sent in paramater
		  ResultSet rs= pstmnt.executeQuery();
		  ArrayList<Status> status_list= new ArrayList<Status>();
		  if(rs!=null){
			  
			  while (rs.next()) {
					Status status_obj = new Status();
					status_obj.setStatusID(rs.getInt(1));
					status_obj.setStatus_desc(rs.getString(2));
					status_obj.setCreated(String.valueOf(rs.getTimestamp(3)));
					status_obj.setAuthor_id(user_id);
					status_obj.setFlag(rs.getInt(5));
					status_list.add(status_obj);	
				}
		  }
		  else{
			  System.out.println("resultset empty");
		  }
		  db.stop();
		  return status_list;
		}
		catch(Exception e){
			
		}
		
		 return  null;
	 }
	 
	 
	
	
	 
	 
	 
	 public String getStatus(int status_ID){
		int statusID=status_ID;
		
	try{
		   boolean check=false;
		   while(check!=true){
			   System.out.println("trying connection");
			   check= db.start();
		   }
		   String status_desc=null;
		   PreparedStatement pstmnt;
	
			pstmnt = db.con.prepareStatement("select status_desc from status where statusID = ?");
			  pstmnt.setInt(1,statusID);
			   ResultSet rs= pstmnt.executeQuery();
			   if(rs!=null){
				   status_desc=rs.getString(1);
				   db.stop();
				   return status_desc;
			   }
			  
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return null;
		   
			      
	   }
	 public boolean addStatus(Status s1){
	  try{
		 boolean check=false;
		 while(check!=true){
			 System.out.println("trying connection");
			check= db.start();
		 }
		 String  status_desc=s1.getStatus_desc(); int user_id=s1.getAuthor_id();
		 
		 String query = "insert into status(status_desc,user_id) values(?, ?)";
		 PreparedStatement pstmnt = db.con.prepareStatement(query);
		 pstmnt.setString(1, status_desc);
		 pstmnt.setInt(2, user_id);
		 pstmnt.executeUpdate();
		 db.stop();
		 return true;
	  }
	  catch (Exception e) 
      {
          System.out.println(e.getMessage());
      }
	 return false;  
   }
	 
	 
	 
	 public boolean removeStatus(Status s1){
		 try{
			 boolean check=false;
			 while(check!=true){
				 System.out.println("trying connection");
				check= db.start();
			 }
			 //String status_id=s1.getStatusID();
			 String query = "UPDATE status SET flag=? where statusID = ?";
	         PreparedStatement pstmnt = db.con.prepareStatement(query);
	         pstmnt.setInt(1,0);
			 pstmnt.setInt(2,s1.getStatusID());
			 pstmnt.executeUpdate();
			
			 db.stop();
			 return true;
		  }
		  catch (Exception e) 
	      {
	          System.out.println(e.getMessage());
	      }
		 return false;  
	   }
}

