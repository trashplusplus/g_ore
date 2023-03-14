package com.example.g_ore;

public class MyUser {

    private String name;
    private String password;
    private int age;

    public MyUser(String name, String password, int age){
        this.name = name;
        this.password = password;
        this.age = age;
    }

    public MyUser(){

    }
    public String getPassword(){
        return password;
    }
    public String getName(){
        return name;
    }

    public int getAge(){
        return age;
    }

    @Override
    public String toString(){
        return String.format("[%s]: {%s}, {%d}", name, password, age);
    }
}
