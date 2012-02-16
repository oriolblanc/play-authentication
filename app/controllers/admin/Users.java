package controllers.admin;
 
import play.*;
import play.mvc.*;
 

import models.Post;
import controllers.CRUD;
import controllers.Secure;

@With(Secure.class)
public class Users extends CRUD {    
}