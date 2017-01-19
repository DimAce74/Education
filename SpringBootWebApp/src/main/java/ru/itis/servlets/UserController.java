package ru.itis.servlets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.ModelAndView;
import ru.itis.models.User;
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
public class UserController{
    @Autowired
    private UsersService usersService;

    @RequestMapping(value = "/users/add", method = RequestMethod.POST, produces = "text/html")
    @ResponseBody ModelAndView addUser(
            @RequestParam("user_name") String userName,
            @RequestParam("user_age") int userAge) {
        usersService.addUser(userName, userAge);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("showUsers");
        return mv;
    }

    @RequestMapping(value = "/users/get", method = RequestMethod.GET, produces = "text/html")
    @ResponseBody ModelAndView showUser(){
        List<User> userList = usersService.findAllUsers();
        ModelAndView mv = new ModelAndView();
        mv.addObject("users", userList);
        mv.setViewName("showUsers");
        return mv;
    }

/**
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        ApplicationContext context = (ApplicationContext)servletConfig.getServletContext().getAttribute("hibernateSpringContext");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userId = (Integer)(request.getAttribute("user_id"));
        String userName = request.getParameter("user_name");
        int userAge = Integer.parseInt(request.getParameter("user_age"));
        User user = usersService.findUser(userId);
        user.setName(userName);
        user.setAge(userAge);
        usersService.updateUser(user);
        response.sendRedirect("/users");

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<User> userList = usersService.findAllUsers();
        request.setAttribute("users", userList);
        request.getRequestDispatcher("/pages/showUsers.jsp").forward(request, response);
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getParameter("user_name");
        int userAge = Integer.parseInt(request.getParameter("user_age"));
        usersService.addUser(userName, userAge);
        response.sendRedirect("/users");
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userId = (Integer)(request.getAttribute("user_id"));
        usersService.deleteUser(userId);
        response.sendRedirect("/users");
    }
*/
}
