package com.example.g_ore;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MyUserServiceImpl implements MyUserService {

    private MyUserDAO myUserDAO;
    public MyUserServiceImpl(MyUserDAO myUserDAO){
        this.myUserDAO = myUserDAO;
    }

    @Transactional
    @Override
    public void save(MyUser myUser) {
        myUserDAO.saveAndFlush(myUser);
    }
    @Transactional
    @Override
    public void delete(MyUser myUser) {

    }
    @Transactional
    @Override
    public List<MyUser> getAllMyUsers() {
        return myUserDAO.findAll();
    }
    @Transactional
    @Override
    public MyUser getById(int id) {
        return null;
    }
}