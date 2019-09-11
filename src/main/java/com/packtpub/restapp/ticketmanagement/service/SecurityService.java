package com.packtpub.restapp.ticketmanagement.service;

public interface SecurityService {
    String createToken(String subject, long ttlMillis);
    String getSubject(String token);
// other methods
}
