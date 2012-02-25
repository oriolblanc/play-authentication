package models;

import java.util.Date;
import javax.persistence.*;

import com.google.gson.JsonObject;

import play.data.validation.Email;
import play.data.validation.Required;
import play.db.jpa.Model;

@Entity
public class FacebookUser extends Model
{
	@Required
    public String fbid;
	
	@Email
	@Required
    public String email;
	
    public String fullname;
    public String firstName;
    public String lastName;
    public String username;
    public String link;
    public String locale;
    public Date birthday;
    public Date updatedTime;

    public FacebookUser(String fbid, String email) {
    	this.fbid = fbid;
    	this.email = email;
    }
    
    public FacebookUser(JsonObject profile)
    {
    	this(profile.get("id").getAsString(), profile.get("email").getAsString());
    	
    	this.fullname = profile.get("name").getAsString();
    	this.firstName = profile.get("first_name").getAsString();
    	this.lastName = profile.get("last_name").getAsString();
    	this.username = profile.get("username").getAsString();
    	this.link = profile.get("link").getAsString();
    	this.locale = profile.get("locale").getAsString();
    	
    	//this.birthday = new DateTime(profile.get("birthday").getAsString());
    }
}
