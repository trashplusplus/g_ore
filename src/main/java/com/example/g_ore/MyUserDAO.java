package com.example.g_ore;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MyUserDAO extends JpaRepository<MyUser, Long> {
    MyUser getMyUserByUsername(String username);

}