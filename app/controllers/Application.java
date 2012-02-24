package controllers;

import play.*;
import play.mvc.*;
import play.mvc.Scope.Session;
import play.modules.facebook.FbGraph;
import play.modules.facebook.FbGraphException;
import play.modules.facebook.Parameter;

import java.util.*;

import com.google.gson.JsonObject;

import models.*;
import play.data.validation.*;

public class Application extends Controller {

    @Before
    static void addDefaults() {
        renderArgs.put("blogTitle", Play.configuration.getProperty("blog.title"));
        renderArgs.put("blogBaseline", Play.configuration.getProperty("blog.baseline"));
    }

    public static void index() {
        Post frontPost = Post.find("order by postedAt desc").first();
        List<Post> olderPosts = Post.find("order by postedAt desc").from(1).fetch(10);
        render(frontPost, olderPosts);
    }
    
    public static void show(Long id) {
        Post post = Post.findById(id);
        render(post);
    }
    
    public static void postComment(Long postId, @Required String author, @Required String content) {
        Post post = Post.findById(postId);
        if(validation.hasErrors()) {
            render("Application/show.html", post);
        }
        post.addComment(author, content);
        flash.success("Thanks for posting %s", author);
        show(postId);
    }
    
    public static void facebookLogin() {
    	Logger.info("entra a la funcio");
        try {
            JsonObject profile = FbGraph.getObject("me"); // fetch the logged in user
            Logger.info("info %s", profile.get("email"));
            String email = profile.get("email").getAsString(); // retrieve the email
            // do useful things
            Session.current().put("username", email); // put the email into the session (for the Secure module)
        } catch (FbGraphException fbge) {
            flash.error(fbge.getMessage());
            if (fbge.getType() != null && fbge.getType().equals("OAuthException")) {
                Session.current().remove("username");
            }
        }
        index();
    }

    public static void facebookLogout() {
        Session.current().remove("username");
        FbGraph.destroySession();
        index();
    }
}