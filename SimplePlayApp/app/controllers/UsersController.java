package controllers;

import models.User;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Result;
import securesocial.core.java.SecuredAction;
import services.UsersService;

import javax.inject.Inject;
import java.util.List;


public class UsersController extends Controller{
    @Inject
    private UsersService usersService;
    @Inject
    private FormFactory formFactory;

    @SecuredAction
    public Result getUsers() {
        List<User> userList = usersService.findAllUsers();
        User user = new User();
        return ok(views.html.showUsers.render(userList));
    }

    @SecuredAction
    public Result addUser() {
        User user = formFactory.form(User.class).bindFromRequest().get();
        usersService.addUser(user.getName());
        return getUsers();
    }

    @SecuredAction
    public Result deleteUser(int userId){
        usersService.deleteUser(userId);
        return redirect("/users");
    }

}
