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


@WebServlet(name = "ShowUsersServlet")
public class ShowUsersServlet extends HttpServlet {
    UsersService usersService;
    private List<User> userList;
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        super.init(servletConfig);
        usersService = UsersServiceFactory.getInstance().getUsersService();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        userList = usersService.findAllUsers();
        request.setAttribute("users", userList);
        request.getRequestDispatcher("/showUsers.jsp").forward(request, response);
    }
}
