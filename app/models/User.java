package models;
 
import java.util.*;
import javax.persistence.*;
 
import play.db.jpa.*;
import play.data.validation.*;
 
@Entity
public class User extends Model {
 
	@Email
	@Required
    public String email;
	
	@Required
    public String fbid;
	
	@Required
    public String oauth;
	
    public String fullname;
    public boolean isAdmin;
    
    public User(String fbid, String oauth) {
        this.fbid = fbid;
        this.oauth = oauth;
    }
    
    public static User connect(String fbid, String oauth) {
        return find("byFbidAndOath", fbid, oauth).first();
    }
    
    public String toString() {
        return email;
    }
}