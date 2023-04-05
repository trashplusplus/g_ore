package com.example.g_ore;

public interface SecurityService {

    String findLoggedInUsername();

    void autoLogin(String username, String password);
}
