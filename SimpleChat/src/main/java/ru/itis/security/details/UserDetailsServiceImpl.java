package ru.itis.security.details;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ru.itis.models.ChatUser;
import ru.itis.services.ChatUserService;

import java.util.ArrayList;
import java.util.List;


public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private ChatUserService chatUserService;

    @Override
    public UserDetails loadUserByUsername(String token) throws UsernameNotFoundException {
        if (chatUserService.isExistsToken(token)){
            ChatUser chatUser = chatUserService.findUserByToken(token);
            List<GrantedAuthority> authorities = createGrantedAuthorities();
            return new UserDetailsImpl(chatUser.getLogin(), chatUser.getPassword(), authorities);
        }
        throw  new UsernameNotFoundException("Token not found!");
    }

    public static List<GrantedAuthority> createGrantedAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("USER"));
        return authorities;
    }
}
