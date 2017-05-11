package controllers;


import play.mvc.Controller;
import play.mvc.Result;

public class LoginController extends Controller {

    public Result index(){
        return ok(views.html.index.render());
    }

    //public Result login(){
    //    return ok(views.html.login.render());
    //}

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
