package com.example.g_ore;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyUserServiceImpl implements MyUserService {

    private final MyUserDAO myUserDAO;

   public MyUserServiceImpl(MyUserDAO myUserDAO) {
      this.myUserDAO = myUserDAO;
    }


    @Override
    public void save(MyUser myUser) {
        myUserDAO.save(myUser);
    }

    @Override
    public void delete(MyUser myUser) {

    }

    @Override
    public List<MyUser> getAllMyUsers() {
       return myUserDAO.findAll();
    }

    @Override
    public MyUser getById(int id) {
        return null;
    }
}