package ru.itis.filters;


import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginFilter implements Filter{

    private final String userID = "admin";
    private final String password = "admin";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String userName = null;
        Cookie[] cookies = request.getCookies();
        if(cookies !=null){
            for(Cookie cookie : cookies){
                if(cookie.getName().equals("user")) {
                    userName = cookie.getValue();
                }
            }
        }
        if (isLoginPage(request)) {
            if (userName == null) {
                filterChain.doFilter(request, response);
            } else {
                response.sendRedirect("/index");
            }
        } else if (isPostLogin(request)) {
            String currentUser = request.getParameter("user");
            String currentPassword = request.getParameter("pwd");

            if (userID.equals(currentUser) && password.equals(currentPassword)) {
                Cookie loginCookie = new Cookie("user", currentUser);
                loginCookie.setMaxAge(30 * 60);
                response.addCookie(loginCookie);
                response.sendRedirect("/index");
            }
        }else if(userName!=null){
            filterChain.doFilter(servletRequest, servletResponse);
        }


    }

    private boolean isLoginPage(HttpServletRequest request) {
        return (request.getMethod().equals("GET")&&(request.getRequestURI().equals("/")||(request.getRequestURI().equals("/login"))));
    }


    private boolean isPostLogin(HttpServletRequest request) {
        return request.getRequestURI().equals("/login") && request.getMethod().equals("POST");
    }

    @Override
    public void destroy() {

    }
}
