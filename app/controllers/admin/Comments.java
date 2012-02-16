package controllers.admin;
 
import play.*;
import play.mvc.*;
import controllers.CRUD;
import controllers.Secure;

@With(Secure.class)
public class Comments extends CRUD {    
}