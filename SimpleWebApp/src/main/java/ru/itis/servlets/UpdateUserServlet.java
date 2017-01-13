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


@WebServlet(name = "UpdateUserServlet")
public class UpdateUserServlet extends HttpServlet {
    UsersService usersService;
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        usersService = UsersServiceFactory.getInstance().getUsersService();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userId = Integer.parseInt(request.getParameter("user_id"));
        String userName = request.getParameter("user_name");
        int userAge = Integer.parseInt(request.getParameter("user_age"));
        User user = usersService.findUser(userId);
        user.setName(userName);
        user.setAge(userAge);
        usersService.updateUser(user);
        response.sendRedirect(request.getContextPath() + "/showUsers");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
