package com.fetch.fbapp3.service;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.fetch.fbapp3.model.User;


public class GetUserDataService {

	public static ArrayList<User> getUserAllData()
	{
		
		//String password=user_obj.getPassword();
		//Statement stmt = null;
		
		try {

      	  DBAccess connect = new DBAccess();
            boolean check=false;
            while(check==false)
            {
            	check=connect.start();
            	System.out.println("trying connection");
            }
			PreparedStatement prepStatement = connect.con
					.prepareStatement("select * from user ");
		

			ResultSet result = prepStatement.executeQuery();
			if (result != null) {
				ArrayList<User> ul = new ArrayList<User>();
				while (result.next()) {
					User u = new User();
					u.setUserID(result.getInt(1));
					u.setEmailID(result.getString(2));
					u.setName(result.getString(4));
					u.setDate(result.getString(5));
					u.setTown(result.getString(6));
					ul.add(u);	
				}
				connect.stop();
				return ul;
			}
			else
				return null;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
        return null;
        
    }//method ends here

	
	
}//class ends here
