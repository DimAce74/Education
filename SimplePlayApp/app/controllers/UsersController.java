package controllers;

import actors.LookupActor;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import com.typesafe.config.ConfigFactory;
import models.User;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Result;
import services.UsersService;

import javax.inject.Inject;
import java.util.List;


public class UsersController extends Controller{
    @Inject
    private UsersService usersService;
    @Inject
    private FormFactory formFactory;

    private ActorSystem system = ActorSystem.create("lookup", ConfigFactory.load("lookup"));
    private String path = "akka.tcp://message@localhost:9001/user/message-actor";

    final ActorRef actor = system.actorOf(Props.create(LookupActor.class, path), "lookupActor");

    public Result getUsers() {

        List<User> userList = usersService.findAllUsers();
        System.out.println(actor);
        actor.tell("Получение списка пользователей", null);
        return ok(views.html.showUsers.render(userList));
    }

    public Result addUser() {
        User user = formFactory.form(User.class).bindFromRequest().get();
        usersService.addUser(user.getName());
        return getUsers();
    }

    public Result deleteUser(int userId){
        usersService.deleteUser(userId);
        return redirect("/users");
    }

}
