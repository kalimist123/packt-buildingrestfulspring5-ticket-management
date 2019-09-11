package com.packtpub.restapp.ticketmanagement.controllers;

import com.packtpub.restapp.ticketmanagement.model.User;
import com.packtpub.restapp.ticketmanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @ResponseBody
    @RequestMapping("")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @ResponseBody
    @RequestMapping("/{id}")
    public User getUserById(@PathVariable("id") Integer id) {

        return userService.getUserById(id);
    }
    @ResponseBody
    @RequestMapping(value="", method= RequestMethod.POST)
    public Map<String, Object> createUser(
            @RequestParam(value="userid") Integer userid,
            @RequestParam(value="username") String username){

        userService.createUser(userid, username);
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("result", "added");
        return map;
    }

}
