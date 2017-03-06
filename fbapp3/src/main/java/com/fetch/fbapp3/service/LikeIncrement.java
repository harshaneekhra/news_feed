package com.fetch.fbapp3.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.fetch.fbapp3.model.likes;

public class LikeIncrement {
    
	DBAccess db= new DBAccess();
	public int incrementLike(likes likeobj){
		boolean check=false;

	    try{
			  while(check!=true){
				 System.out.println("trying connection in LikeIncrement");
				 check= db.start();
				 
			  }
			  
			String query1="select * from likes where statusID=? and userID=?";  
			PreparedStatement pstmnt1=db.con.prepareStatement(query1);
			pstmnt1.setInt(1, likeobj.getLstatusID());
			pstmnt1.setInt(2,likeobj.getLuserID());
			ResultSet rs1= pstmnt1.executeQuery();
				 rs1.last();
				 int rows = rs1.getRow();
				 rs1.beforeFirst();
		    if(rows==0){
		    	System.out.println("Inside likeIncrement service and inserting a like entry");
		    	String query2= "insert into likes(statusID,userID)values(?,?)";
		    	PreparedStatement pstmnt2=db.con.prepareStatement(query2);
		    	pstmnt2.setInt(1,likeobj.getLstatusID());
		    	pstmnt2.setInt(2, likeobj.getLuserID());
		    	pstmnt2.executeUpdate();
		    	 db.stop();
		    	 return 1;
		    	
		    }
		    else{
		    	System.out.println("Inside likeIncrement service and updating a like entry to unlike");
		    	String query3= "update likes SET flag=1 where statusID =? and userID=?";
		    	PreparedStatement pstmnt3=db.con.prepareStatement(query3);
		    	pstmnt3.setInt(1,likeobj.getLstatusID());
		    	pstmnt3.setInt(2, likeobj.getLuserID());
		    	pstmnt3.executeUpdate();
		    	 db.stop();
		    	 return 1;
		    }
			 
		} catch (SQLException e) 
	    {
	        e.printStackTrace();
	    }
		 return 0;
	}
}
