package controllers;
 
import play.*;
import play.mvc.*;
 
import java.util.*;
 
import models.*;
 
@With(Secure.class)
public class Admin extends Controller 
{    
    @Before
    static void setConnectedUser() {
        if(Security.isConnected()) {
            User user = User.find("byFbid", Security.connected()).first();
            
            if(user != null)
            {
            	renderArgs.put("user", user.facebookUser.fullname);
            }
        }
    }
 
    public static void index() {
        render();
    }    
}