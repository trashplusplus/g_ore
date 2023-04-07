package com.example.g_ore;

import java.util.List;

public interface MyUserService {
    void save(MyUser myUser);
    void delete(MyUser myUser);

    //void update(MyUser myUser);
    List<MyUser> getAllMyUsers();
    MyUser getById(long id);
    MyUser findByUsername(String username);



    void flush();

}