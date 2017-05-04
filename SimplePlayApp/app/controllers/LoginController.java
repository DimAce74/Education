package controllers;


import play.mvc.Controller;
import play.mvc.Result;
import securesocial.core.java.SecuredAction;

public class LoginController extends Controller {

    @SecuredAction
    public Result index(){
        return ok(views.html.index.render());
    }

    //public Result login(){
    //    return ok(views.html.login.render());
    //}

    @SecuredAction
    public Result loginToIndex(){
        return index();
    }
/*
    public Result logout() {
        Cookie loginCookie = request().cookies().get("user");

        if (loginCookie != null) {
            response().discardCookie("user");
        }
        return redirect("/login");
    }
    */
}
