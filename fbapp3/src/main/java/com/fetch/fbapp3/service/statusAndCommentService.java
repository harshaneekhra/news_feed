package com.fetch.fbapp3.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.fetch.fbapp3.model.Status;
import com.fetch.fbapp3.model.comment;

public class statusAndCommentService {

	DBAccess db= new DBAccess();
	public ArrayList<Status> getStatus(int user_id)
	{
	   /* PreparedStatement ps1 = null;
	    ResultSet rs1 = null;
	    PreparedStatement ps2 = null;
	    ResultSet rs2 = null;*/
		String rs1;
		boolean check=false;
	    

	    try{
			  while(check!=true){
				  System.out.println("trying connection in getStatus");
				 check= db.start();
			  }
	    
			  String query1="select * from status where user_id= ?";

	   
			  PreparedStatement pstmnt=db.con.prepareStatement(query1);
			  pstmnt.setInt(1,user_id); // user_id is the one sent in paramater
			  ResultSet rs= pstmnt.executeQuery();
			  ArrayList<Status> statusArrayList = new ArrayList<Status>();

	        while (rs.next())
	        {   System.out.println("inside first result set");
	        	Status status_obj = new Status();
				status_obj.setStatusID(rs.getInt(1));
				status_obj.setStatus_desc(rs.getString(2));
				status_obj.setCreated(String.valueOf(rs.getTimestamp(3)));
				status_obj.setAuthor_id(user_id);
				status_obj.setFlag(rs.getInt(5));
				//statusArrayList.add(status_obj);	

	         
	            ArrayList<comment> commentArrayList = new ArrayList<comment>();
	            comment comment_obj;
                int stID=rs.getInt(1);
                System.out.println("*****"+stID+"***");
                
                /**********LIKES************************/
	            
	            
	            String query3="select count(likeID) from likes where statusID=?";

		     	   
				  PreparedStatement pstmnt3=db.con.prepareStatement(query3);
				  pstmnt3.setInt(1,stID); // user_id is the one sent in paramater
				  ResultSet rs3= pstmnt3.executeQuery();
				  /*if(!rs3.next())
				  {
					  status_obj.setLikesCounts(0); 
				  }*/
	             while(rs3.next())
	             {   System.out.println("Inside rs3. while ");
	            	 status_obj.setLikesCount(rs3.getInt(1)); 
	             }

                /*********comments************/
	            String query2 = "select * from comments where sID = ?";

	            /*ps2 = conn.prepareStatement(query2);
	            ps2.setInt(1, employeeID);
	            rs2 = ps2.executeQuery();*/
	            PreparedStatement pstmnt2=db.con.prepareStatement(query2);
				  pstmnt2.setInt(1,stID); // user_id is the one sent in paramater
				 ResultSet rs2= pstmnt2.executeQuery();
                  // int count= pstmnt2.executeUpdate();
                  // System.out.println(count);
				 rs2.last();
				 int rows = rs2.getRow();
				 rs2.beforeFirst();
				 System.out.println("row count:"+rows);
				 
                
                	
	            while (rs2.next())
	            {   
	            	comment_obj=new comment();
	            	System.out.println("inside second result set");
	                
	                comment_obj.setCommentID(rs2.getInt(1));
	                comment_obj.setUserID(rs2.getInt(3));
					comment_obj.setComment_desc(rs2.getString(4));
					comment_obj.setFlag(rs2.getInt(5));
	                   
	                commentArrayList.add(comment_obj);   
	                for(comment clist:commentArrayList){
	                	System.out.print("comment list contains "+clist.getComment_desc()+" "+clist.getCommentID()+",");
	                }
	                System.out.println();
	            }
	            
                
                System.out.println("Caling comment class method");
               
	            status_obj.setA(commentArrayList);
	            
	          
	            statusArrayList.add(status_obj);    
	        }
	        db.stop();
	        return statusArrayList;
	    } 
	    catch (SQLException e) 
	    {
	        e.printStackTrace();
	    }
	   

	    return null;    
	}
}