package com.example.g_ore;

import java.util.List;


public interface MyUserService {
    void save(MyUser myUser);
    void delete(MyUser myUser);
    List<MyUser> getAllMyUsers();

    MyUser getById(int id);
}