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
	
    public String fullname;
    public boolean isAdmin;
    
    public User(String email, String fbid, String fullname) {
        this.email = email;
        this.fbid = fbid;
        this.fullname = fullname;
    }
    
    public static User connect(String email, String fbid) {
        return find("byEmailAndFbid", email, fbid).first();
    }
    
    public String toString() {
        return email;
    }
}