package com.packtpub.restapp.ticketmanagement.controllers;

import com.packtpub.restapp.ticketmanagement.errorhanders.ErrorHandler;
import com.packtpub.restapp.ticketmanagement.model.User;
import com.packtpub.restapp.ticketmanagement.model.UserType;
import com.packtpub.restapp.ticketmanagement.service.SecurityService;
import com.packtpub.restapp.ticketmanagement.service.UserService;
import com.packtpub.restapp.ticketmanagement.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController  extends ErrorHandler {

    @Autowired
    private UserService userService;

    @Autowired
    SecurityService securityService;

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
    @RequestMapping(value = "/register/admin", method = RequestMethod.POST)
    public Map<String, Object> registerAdmin(
            @RequestParam(value = "username") String username,
            @RequestParam(value = "password") String password
    ) {

        userService.createUser(username, password, 3);

        return Util.getSuccessResult();
    }


    @ResponseBody
    @RequestMapping(value = "/register/customer", method = RequestMethod.POST)
    public Map<String, Object> registerCustomer(
            @RequestParam(value = "username") String username,
            @RequestParam(value = "password") String password
    ) {

        userService.createUser(username, password, 1);
        return Util.getSuccessResult();
    }

    @ResponseBody
    @RequestMapping(value = "/register/csr", method = RequestMethod.POST)
    public Map<String, Object> registerCSR(
            @RequestParam(value = "username") String username,
            @RequestParam(value = "password") String password
    ) {

        userService.createUser(username, password, 2);
        return Util.getSuccessResult();
    }

    @ResponseBody
    @RequestMapping(value = "/login/customer", method = RequestMethod.POST)
    public Map<String, Object> loginCustomer(
            @RequestParam(value = "username") String username,
            @RequestParam(value = "password") String password
    ) {
        User user = userService.getUser(username, password, 1);
        if(user == null){
            return Util.getUserNotAvailableError();
        }
        String subject = user.getUserid()+"="+user.getUsertype();
        String token = securityService.createToken(subject, (15 * 1000 * 60));
// 15 minutes expiry time
        return Util.getSuccessResult(token);
    }


    @ResponseBody
    @RequestMapping(value = "/login/admin", method = RequestMethod.POST)
    public Map<String, Object> loginAdmin(
            @RequestParam(value = "username") String username,
            @RequestParam(value = "password") String password
    ) {
        Map<String, Object> map = new LinkedHashMap<>();
        User user = userService.getUser(username, password, 3);
        if(user == null){
            return Util.getUserNotAvailableError();
        }
        String subject = user.getUserid()+"="+user.getUsertype();
        String token = securityService.createToken(subject, (15 * 1000 * 60));
// 15 mins expiry time
        map.put("result_code", 0);
        map.put("result", "success");
        map.put("token", token);
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/login/csr", method = RequestMethod.POST)
    public Map<String, Object> loginCSR(
            @RequestParam(value = "username") String username,
            @RequestParam(value = "password") String password
    ) {
        User user = userService.getUser(username, password, 2);
        if(user == null){
            return Util.getUserNotAvailableError();
        }
        String subject = user.getUserid()+"="+user.getUsertype();
        String token = securityService.createToken(subject, (15 * 1000 * 60));
// 15 mins expiry time
        return Util.getSuccessResult(token);
    }
}
