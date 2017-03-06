package com.fetch.fbapp3.resources;

import java.io.IOException;
import java.util.ArrayList;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fetch.fbapp3.model.Status;
import com.fetch.fbapp3.model.comment;
import com.fetch.fbapp3.model.likes;
import com.fetch.fbapp3.service.AddCommentService;
import com.fetch.fbapp3.service.LikeDecrement;
import com.fetch.fbapp3.service.LikeIncrement;
import com.fetch.fbapp3.service.StatusService;
import com.fetch.fbapp3.service.statusAndCommentService;

@WebService()
@Path("/status")
public class StatusResouce {
    private statusAndCommentService ss;
    private StatusService sservice;
    private AddCommentService addCommentService;
    private LikeIncrement liservice;
    private LikeDecrement ldservice;
   
    public StatusResouce() {
    	ss=new statusAndCommentService();
    	sservice=new StatusService();
    	 liservice = new LikeIncrement();
    	 ldservice = new LikeDecrement();
    	 addCommentService=new AddCommentService();
	}
    
    
    @POST
    @Path("/addComment")
    @WebMethod(operationName = "insert")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public boolean addComment(comment cmt)throws JsonParseException, JsonMappingException, IOException{
    	System.out.println("inside addComment");
    	cmt.setComment_desc(cmt.getComment_desc());
    	//status.setAuthor_id(status.getAuthor_id());
    	cmt.setCommentID(cmt.getCommentID());
    	if(addCommentService.addComment(cmt)){
    	   System.out.println("upto resource comment addes successfully");
    		return true;
    	}
    	else
    		return false;
    	
    }
    
    
    
     
    @GET
    @Path("/{user_id}")
    //  @Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
   // @WebMethod(operationName = "fetch")
    public ArrayList<Status> getStatusByUser(@PathParam("user_id")int user_id) throws JsonParseException, JsonMappingException, IOException
    {
    	
    	System.out.println("Inside fetch method ");
  		 ArrayList<Status> status_list= ss.getStatus(user_id);
  		 
		 System.out.println("length is :"+status_list.size());
		//Enc.Encrpt(u1);
		 for(Status status :status_list){
			 System.out.println(status.getStatusID()+" "+status.getAuthor_id()+" "+status.getLikesCount());
			 for(comment cmnt: status.getA()){
				 
				 System.out.println("here"+cmnt.getCommentID()+" "+cmnt.getComment_desc());
			 }
		 }
		 
		return status_list;
		   
    }
    
    @POST
    @Path("/incrementLike")
    @WebMethod(operationName = "insert")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.TEXT_HTML})
    public boolean incrementLike(likes likeobj)throws JsonParseException, JsonMappingException, IOException{
    	 System.out.println("inside increment like resource");
    	 likeobj.setLstatusID(likeobj.getLstatusID());
     	 likeobj.setLuserID(likeobj.getLuserID());
    	 if(liservice.incrementLike(likeobj)==1){
    		 return true;
    	 }
    	 else 
    	     return false;
    }
    
    @POST
    @Path("/decrementLike")
    @WebMethod(operationName = "ld")
    @Consumes({MediaType.APPLICATION_JSON})
    public boolean decrementLike(likes likeobj)throws JsonParseException, JsonMappingException, IOException{
    	 
    	 if(ldservice.dislike(likeobj)==1){
    		 return true;
    	 }
    	 else 
    	     return false;
    }
    
    @POST
    @Path("/addStatus")
    @WebMethod(operationName = "insert")
    @Consumes({MediaType.APPLICATION_JSON})
    public boolean addStatus(Status status)throws JsonParseException, JsonMappingException, IOException{
    	System.out.println("hello");
    	status.setStatus_desc(status.getStatus_desc());
    	System.out.println(status.getStatus_desc());
    	status.setAuthor_id(status.getAuthor_id());
    	if(sservice.addStatus(status)){
    	    System.out.println("here");
    		return true;
    	}
    	else
    		return false;
    	
    } // end of addStatus
    
    @PUT
    @Path("/remove")
	@WebMethod(operationName = "remove")
	@Consumes({MediaType.APPLICATION_JSON})
    public boolean removeStatus(Status status){
    	System.out.println("updating user info");
		//String output = "POST:Jersey say : " + msg;
		status.setAuthor_id(status.getAuthor_id());
		status.setCreated(status.getCreated());
		status.setStatus_desc(status.getStatus_desc());
		status.setStatusID(status.getStatusID());
		status.setFlag(status.getFlag());
		if(sservice.removeStatus(status)){
			return true;
		}
		return false;
		
    }
    
}
