package controllers;


import models.Auto;
import models.User;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Result;
import services.AutoService;
import services.UsersService;

import javax.inject.Inject;
import java.util.List;


public class AutoController extends Controller{

    @Inject
    private UsersService usersService;
    @Inject
    private AutoService autoService;
    @Inject
    private FormFactory formFactory;


    public Result showUserAuto(int userId) {
        User user = usersService.findUser(userId);
        List<Auto> autoList = user.getListAuto();
        return ok(views.html.showAuto.render(user, autoList));
    }

    public Result addAuto(int userId)  {
        User user = usersService.findUser(userId);
        Auto auto = formFactory.form(Auto.class).bindFromRequest().get();
        auto.setUser(user);
        autoService.addAuto(auto);
        return showUserAuto(userId);
    }

    public Result showAllAuto(){
        List<Auto> autoList = autoService.showAllAuto();
        return ok(views.html.showAuto.render(null, autoList));
    }


    public Result deleteAuto(int userId, int autoId) {
        autoService.deleteAuto(autoId);
        return redirect("/users/"+userId+"/autos");
    }

}
