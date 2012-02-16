package controllers.admin;
 
import controllers.CRUD;
import controllers.Secure;
import play.*;
import play.mvc.*;
 
@With(Secure.class)
public class Posts extends CRUD {    
}
