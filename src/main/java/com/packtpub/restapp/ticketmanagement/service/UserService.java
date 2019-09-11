package com.packtpub.restapp.ticketmanagement.service;

import com.packtpub.restapp.ticketmanagement.model.User;
import com.packtpub.restapp.ticketmanagement.model.UserType;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    User getUserById(int id);
    User getUser(String username, String password, Integer usertype);
    void createUser(String username,String password, Integer usertype );
    User getUserByToken(String token);
}
