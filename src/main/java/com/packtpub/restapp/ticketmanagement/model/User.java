package com.packtpub.restapp.ticketmanagement.model;

public class User {
    public User(Integer userid, Integer usertype) {
        this.userid = userid;
        this.usertype = usertype;
    }

    private Integer userid;
    private String username;
    private String password;
    private static Integer userCounter = 100;

    public User(String username, String password, Integer usertype) {
        userCounter++;
        this.userid = userCounter;
        this.username = username;
        this.password = password;
        this.usertype = usertype;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getUsertype() {
        return usertype;
    }

    public void setUsertype(Integer usertype) {
        this.usertype = usertype;
    }

    /*
     * usertype:
     * 1 - general user
     * 2 - CSR (Customer Service Representative)
     * 3 - admin
     */
    private Integer usertype;

    public User(Integer userid, String username) {
        this.userid = userid;
        this.username = username;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }



}