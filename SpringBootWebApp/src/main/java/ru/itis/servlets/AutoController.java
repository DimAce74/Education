package ru.itis.servlets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import ru.itis.config.SpringAppConfig;
import ru.itis.models.Auto;
import ru.itis.models.User;
import ru.itis.services.AutoService;
import ru.itis.services.UsersService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
public class AutoController {
    @Autowired
    private AutoService autoService;
    @Autowired
    private UsersService usersService;

/**
    @Override
    public void init(ServletConfig config) throws ServletException {
        ApplicationContext context = (ApplicationContext)config.getServletContext().getAttribute("hibernateSpringContext");
        usersService = (UsersService) context.getBean("usersService");
        autoService = (AutoService) context.getBean("autoService");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int autoId = (Integer)(request.getAttribute("auto_id"));
        String model = request.getParameter("model");
        String color = request.getParameter("color");
        Auto auto = autoService.findAuto(autoId);
        auto.setModel(model);
        auto.setColor(color);
        autoService.updateAuto(auto);
        response.sendRedirect("/users/"+auto.getUser().getId()+"/autos");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Auto> autoList;
        if (request.getAttribute("user_id")== null){
            autoList = autoService.showAllAuto();
        }else {
            int userId = (Integer) (request.getAttribute("user_id"));
            autoList = usersService.showUsersAutoById(userId);
        }
        request.setAttribute("autos", autoList);
        request.getRequestDispatcher("/showAuto.jsp").forward(request, response);
    }
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String model = req.getParameter("model");
        String color = req.getParameter("color");
        int userId = (Integer) (req.getAttribute("user_id"));
        User user = usersService.findUser(userId);
        Auto auto = new Auto(model, color, user);
        autoService.addAuto(auto);
        resp.sendRedirect("/users/"+userId+"/autos");
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int autoId = (Integer)req.getAttribute("auto_id");
        int userId = autoService.findAuto(autoId).getUser().getId();
        autoService.deleteAuto(autoId);
        resp.sendRedirect("/users/"+userId+"/autos");
    }
 */
}
