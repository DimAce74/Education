package ru.itis.security.provider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import ru.itis.security.auth.TokenAuthentication;


public class TokenAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserDetailsService userDetailsService;

    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        TokenAuthentication tokenAuthentication = (TokenAuthentication)authentication;
        String token = (String)tokenAuthentication.getPrincipal();
        UserDetails userDetails = userDetailsService.loadUserByUsername(token);
        if (userDetails == null) {
            throw new IllegalArgumentException("User not found");
        }
        tokenAuthentication.setDetails(userDetails);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return authentication;
    }

    public boolean supports(Class<?> aClass) {
        return aClass.equals(TokenAuthentication.class);
    }
}
