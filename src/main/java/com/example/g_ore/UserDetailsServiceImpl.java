package com.example.g_ore;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.HashSet;


@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    private final MyUserDAO userDAO;

    public UserDetailsServiceImpl(MyUserDAO userDAO){
        this.userDAO = userDAO;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MyUser user = userDAO.getMyUserByUsername(username);

        HashSet<GrantedAuthority> roles = new HashSet<>();
        roles.add(new SimpleGrantedAuthority(user.getAuthority()));

        return new User(user.getUsername(), user.getPassword(), roles);
    }
}
