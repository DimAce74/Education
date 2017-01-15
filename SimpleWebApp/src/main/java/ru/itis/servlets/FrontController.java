package ru.itis.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "FrontController")
public class FrontController extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requstURI = request.getRequestURI();
        String[] partsOfURI = requstURI.split("/");
        if (requstURI.equals("/users")) {
            request.getRequestDispatcher("/users").forward(request, response);
        } else if (requstURI.equals("/autos")) {
            request.getRequestDispatcher("/autos").forward(request, response);
        } else if (partsOfURI.length == 3) {
            if (partsOfURI[1].equals("users")) {
                if (partsOfURI[2].equals("add")){
                    request.getRequestDispatcher("/users").forward(request, response);
                } else {
                    int userId = Integer.parseInt(partsOfURI[2]);
                    request.setAttribute("user_id", userId);
                    request.getRequestDispatcher("/users").forward(request, response);
                }
            } else if (partsOfURI[1].equals("autos")) {
                int autoId = Integer.parseInt(partsOfURI[2]);
                request.setAttribute("auto_id", autoId);
                request.getRequestDispatcher("/autos").forward(request, response);
            }
        } else if (partsOfURI.length == 4) {
            int userId = Integer.parseInt(partsOfURI[2]);
            request.setAttribute("user_id", userId);
            request.getRequestDispatcher("/autos").forward(request, response);
        }

    }
}

