package ru.itis.servlets;

import ru.itis.factories.AutoServiceFactory;
import ru.itis.factories.UsersServiceFactory;
import ru.itis.models.Auto;
import ru.itis.services.AutoService;
import ru.itis.services.UsersService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AddAutoServlet")
public class AddAutoServlet extends HttpServlet {
    AutoService autoService;
    UsersService usersService;

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        super.init(servletConfig);
        autoService= AutoServiceFactory.getInstance().getAutoService();
        usersService=UsersServiceFactory.getInstance().getUsersService();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String model = request.getParameter("model");
        String color = request.getParameter("color");
        int userId = Integer.parseInt(request.getParameter("user_id"));
        autoService.addAuto(new Auto(model, color, usersService.findUser(userId)));

        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
