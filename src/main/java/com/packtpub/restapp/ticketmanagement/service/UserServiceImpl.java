package com.packtpub.restapp.ticketmanagement.service;

import com.packtpub.restapp.ticketmanagement.model.User;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
@Service
public class UserServiceImpl implements UserService {

    public static List<User> users;
    public UserServiceImpl() {
        users = new LinkedList<>();
        users.add(new User(100, "David"));
        users.add(new User(101, "Peter"));
        users.add(new User(102, "John"));
    }

    @Override
    public List<User> getAllUsers() {
        return users;
    }

    @Override
    public User getUserById(int id) {
        return users.stream()
                .filter(x->x.getUserid()==id)
                .findAny()
                .orElse(new User(0, "not available"));
    }

    @Override
    public void createUser(Integer userid, String username) {
        users.add(new User(userid,username));

    }
}
