package com.packtpub.restapp.ticketmanagement.service;

import com.packtpub.restapp.ticketmanagement.model.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    User getUserById(int id);
    void createUser(Integer userid, String username);
}
