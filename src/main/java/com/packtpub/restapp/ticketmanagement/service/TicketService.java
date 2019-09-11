package com.packtpub.restapp.ticketmanagement.service;

import com.packtpub.restapp.ticketmanagement.model.Ticket;

import java.util.List;

public interface TicketService {
    void addTicket(Integer creatorid, String content, Integer severity, Integer status);

    List<Ticket> getMyTickets(Integer creatorid);

    Ticket getTicket(Integer creatorid, Integer ticketid);

    Ticket getTicket(Integer ticketid);

    void updateTicket(Integer ticketid, String content, Integer
            severity, Integer status);

}

