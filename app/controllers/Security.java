package controllers;
 
import models.*;
 
public class Security extends Secure.Security 
{	
	static boolean authenticate(String fbid, String oauth) 
	{
	    boolean userIsInOurSystem = User.connect(fbid, oauth) != null;
	    
	    if(!userIsInOurSystem)
	    {
	    	new User(fbid, oauth).save();
	    }
	    return true;
	}
    
	static void onDisconnected() {
	    Application.index();
	}

	static void onAuthenticated() {
	    Admin.index();
	}
	
	static boolean check(String profile) {
	    if("admin".equals(profile)) {
	        return User.find("byEmail", connected()).<User>first().isAdmin;
	    }
	    return false;
	}
}