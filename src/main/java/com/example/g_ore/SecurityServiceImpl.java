package com.example.g_ore;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;


public class SecurityServiceImpl implements SecurityService {

    private AuthenticationManager authenticationManager;
    private UserDetailsService userDetailsService;

    public SecurityServiceImpl(AuthenticationManager authenticationManager, UserDetailsService userDetailsService){
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
    }

    @Override
    public String findLoggedInUsername() {
        Object userDetails = SecurityContextHolder.getContext().getAuthentication();
        if(userDetails instanceof UserDetails){
            return ((UserDetails) userDetails).getUsername();
        }

        return null;
    }

    @Override
    public void autoLogin(String username, String password) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        UsernamePasswordAuthenticationToken authenticationToken
                = new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());

        authenticationManager.authenticate(authenticationToken);

        if(authenticationToken.isAuthenticated()){
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);

            System.out.printf("User %s authenticated", username);
        }
    }
}
