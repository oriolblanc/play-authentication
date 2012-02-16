package controllers;
 
import play.*;
import play.mvc.*;
 

import models.Post;
import controllers.CRUD;
import controllers.Secure;

@Check("admin")
@With(Secure.class)
public class Users extends CRUD {    
}