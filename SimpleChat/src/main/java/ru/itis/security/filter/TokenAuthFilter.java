package ru.itis.security.filter;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.filter.GenericFilterBean;
import ru.itis.exceptions.AuthException;
import ru.itis.exceptions.TokenNotFoundException;
import ru.itis.security.auth.TokenAuthentication;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


public class TokenAuthFilter extends GenericFilterBean {
    private static final String header = "Auth-Token";

    private AuthenticationManager authenticationManager;

    public TokenAuthFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        System.out.println(httpServletRequest.getRequestURI());
        try {
            String headerValue = httpServletRequest.getHeader(header);

            if (isNotRequiringProtection(httpServletRequest)) {
                filterChain.doFilter(servletRequest, servletResponse);
            } else if (headerValue == null || headerValue.equals("")) {
                throw new TokenNotFoundException();
            } else {
                authenticationManager.authenticate(new TokenAuthentication(headerValue));
                filterChain.doFilter(servletRequest, servletResponse);
            }
        } catch (AuthenticationException authenticationException) {
            throw new AuthException();
        }
    }

    private boolean isNotRequiringProtection(HttpServletRequest request) {
        return request.getRequestURI().startsWith("/newuser") && request.getMethod().equals("POST")
                || request.getRequestURI().endsWith("favicon.ico")
                || request.getRequestURI().startsWith("/login") && request.getMethod().equals("POST");
    }
}
