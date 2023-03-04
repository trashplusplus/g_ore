package com.example.g_ore;

import java.util.ArrayList;

public class UserDAO {
    private ArrayList<MyUser> users;

    public UserDAO(){
        users = new ArrayList<>();
    }

    public ArrayList<MyUser> getUsers() {
        return users;
    }

    public void add(MyUser user){
        users.add(user);
    }

}
