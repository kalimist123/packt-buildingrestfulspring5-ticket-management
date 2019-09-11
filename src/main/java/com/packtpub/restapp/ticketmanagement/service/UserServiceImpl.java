package com.packtpub.restapp.ticketmanagement.service;

import com.packtpub.restapp.ticketmanagement.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Service;

import javax.xml.bind.DatatypeConverter;
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
    public User getUser(String username, String password, Integer usertype) {
        return users.stream()
                .filter(x -> x.getUsername().equalsIgnoreCase(username) && x.getPassword().equalsIgnoreCase(password)  && x.getUsertype() == usertype )
                .findAny()
                .orElse(null);
    }

    @Override
    public void createUser(String username, String password, Integer usertype) {
        User user = new User(username, password, usertype);
        this.users.add(user);

    }

    @Override
    public User getUserByToken(String token){
        Claims claims = Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(SecurityServiceImpl.secretKey))
                .parseClaimsJws(token).getBody();

        if(claims == null || claims.getSubject() == null){
            return null;
        }

        String subject = claims.getSubject();

        if(subject.split("=").length != 2){
            return null;
        }

        String[] subjectParts = subject.split("=");


        Integer userid = new Integer(subjectParts[0]);
        Integer usertype = new Integer(subjectParts[1]);

        System.out.println("{getUserByToken} usertype["+usertype+"], userid["+userid+"]");

        return new User(userid, usertype);
    }

}
