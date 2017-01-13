package ru.itis.servlets;

import ru.itis.factories.UsersServiceFactory;
import ru.itis.models.Auto;
import ru.itis.services.UsersService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet(name = "ShowAutoServlet")
public class ShowAutoServlet extends HttpServlet {
    UsersService usersService;
    private List<Auto> autoList;

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        super.init(servletConfig);
        usersService = UsersServiceFactory.getInstance().getUsersService();

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userId = Integer.parseInt(request.getParameter("user_id"));
        autoList= usersService.showUsersAutoById(userId);
        request.setAttribute("auto_list", autoList);
        request.getRequestDispatcher("/showAuto.jsp").forward(request, response);

    }
}
