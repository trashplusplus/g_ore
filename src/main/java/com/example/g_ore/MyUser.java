package com.example.g_ore;


public class MyUser {

    private String name;
    private String password;

    public MyUser(String name, String password){
        this.name = name;
        this.password = password;
    }


    public MyUser(){

    }

    public String getPassword(){
        return password;
    }
    public String getName(){
        return name;
    }


}
