package controllers;
 
import play.*;
import play.mvc.*;
import controllers.CRUD;
import controllers.Secure;

@Check("admin")
@With(Secure.class)
public class Comments extends CRUD {    
}	