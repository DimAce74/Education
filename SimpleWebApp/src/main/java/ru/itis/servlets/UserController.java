package ru.itis.servlets;

import ru.itis.factories.UsersServiceFactory;
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


@WebServlet(name = "UserController")
public class UserController extends HttpServlet {
    private UsersService usersService;
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        super.init(servletConfig);
        usersService = UsersServiceFactory.getInstance().getUsersService();
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
        request.getRequestDispatcher("/showUsers.jsp").forward(request, response);
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

}
