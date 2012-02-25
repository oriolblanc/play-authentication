package models;
 
import java.util.*;
import javax.persistence.*;

import com.google.gson.JsonObject;
 
import play.Logger;
import play.db.jpa.*;
import play.data.validation.*;
 
@Entity
public class User extends Model {
	@Required
    public String fbid;
	
	@Required
    public String oauth;
	
	@Required
	@OneToOne
    public FacebookUser facebookUser;
    
    public boolean isAdmin;
    
    public User(String fbid, String oauth) {
    	Logger.info("user logged with fbid:%s and accesstoken: %s", fbid, oauth);
        this.fbid = fbid;
        this.oauth = oauth;
    }
  
    public User(JsonObject profile) {
        this(profile.get("id").getAsString(), "");
        FacebookUser facebookUser = new FacebookUser(profile);
        this.setFacebookUser(facebookUser);
    }
    
    public void setFacebookUser(FacebookUser facebookUser)
    {
    	this.facebookUser = facebookUser;
    }
    
    public static User connect(String fbid, String oauth) {
        return find("byFbid", fbid).first();
    }
    
    public String toString() {
        return fbid;
    }
    
    public String getFullName()
    {
    	return facebookUser.fullname;
    }
}