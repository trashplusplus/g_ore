package com.example.g_ore;


import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class MyUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;

    public MyUser(){

    }

    @Override
    public String toString(){
        return String.format("[%d] %s | %s", id, username, password);
    }


}
