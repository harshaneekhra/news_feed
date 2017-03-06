package org.ex.javabrains.messenger.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.ex.javabrains.messenger.model.Status;

public class StatusService {
	 DBAccess db= new DBAccess();
	 public String getStatus(Long status_ID){
		 String statusID=String.valueOf(status_ID);
		
		   boolean check=false;
		   while(check!=true){
			   System.out.println("trying connection");
			   check= db.start();
		   }
		   String status_desc=null;
		   PreparedStatement pstmnt= db.con.prepareStatement("select status_desc from status where statusID = ?");
		   pstmnt.setString(1,statusID);
		   ResultSet rs= pstmnt.executeQuery();
		   if(rs!=null){
			   status_desc=rs.getString(1);
			   return status_desc;
		   }
		   else 
			   return null;
		   
		   db.close();
		   
			      
	   }
	 public boolean addStatus(Status s1){
	  try{
		 boolean check=false;
		 while(check!=true){
			 System.out.println("trying connection");
			check= db.start();
		 }
		 String statusID=s1.getStatusID(), status_desc=s1.getStatus_desc(), user_id=s1.getAuthor_id();
		 
		 String query = "insert into status(status_desc,user_id) values(?, ?)";
		 PreparedStatement pstmnt = db.con.prepareStatement(query);
		 pstmnt.setString(1, status_desc);
		 pstmnt.setString(2, user_id);
		 pstmnt.executeUpdate();
		 db.close();
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
			 String status_id=s1.getStatusID();
			  String query = "UPDATE status SET flag=? where statusID = ?";
	         PreparedStatement pstmnt = db.con.prepareStatement(query);
	         pstmnt.setString(1,"0");
			 pstmnt.setString(2,s1.getStatusID());
			 pstmnt.executeUpdate();
			
			 db.close();
			 return true;
		  }
		  catch (Exception e) 
	      {
	          System.out.println(e.getMessage());
	      }
		 return false;  
	   }
}

