package ru.itis.servlets;

import ru.itis.factories.UsersServiceFactory;
import ru.itis.models.User;
import ru.itis.services.UsersService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;


@WebServlet(name = "MyFirstServlet")
public class MyFirstServlet extends HttpServlet {
    UsersService usersService = UsersServiceFactory.getInstance().getUsersService();
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        List<User> userList = usersService.findAllUsers();
        PrintWriter pw = resp.getWriter();
        pw.println("<H1>Список пользователей:</H1>");
        pw.println("<table border=1>");
        pw.println("<tr>");
        pw.println("<td>ID</td>");
        pw.println("<td>Name</td>");
        pw.println("<td>Age</td>");
        pw.println("<td>Autos count</td>");
        pw.println("<tr>");
        for (User user : userList){
            pw.println("<tr>");
            pw.println("<td>" + user.getId()+"</td>");
            pw.println("<td>" + user.getName()+"</td>");
            pw.println("<td>" + user.getAge()+"</td>");
            pw.println("<td>" + user.getListAuto().size()+"</td>");
            pw.println("<tr>");
        }
    }
}
