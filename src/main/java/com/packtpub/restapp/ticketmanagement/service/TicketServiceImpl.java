package com.packtpub.restapp.ticketmanagement.service;

import com.packtpub.restapp.ticketmanagement.model.Ticket;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TicketServiceImpl implements TicketService {

    // Dummy tickets
    public static List<Ticket> tickets;

    public TicketServiceImpl() {
        tickets = new LinkedList<>();

    }

    @Override
    public void addTicket(Integer creatorid, String content, Integer severity, Integer status) {
        Ticket ticket = new Ticket(creatorid, new Date(), content, severity,
                status);
        tickets.add(ticket);
    }

    @Override
    public List<Ticket> getMyTickets(Integer creatorid) {
        return tickets.stream()
                .filter(x -> x.getCreatorid().intValue() == creatorid.intValue())
                .collect(Collectors.toList());
    }

    @Override
    public Ticket getTicket(Integer creatorid, Integer ticketid) {
        return tickets.stream()
                .filter(x -> x.getCreatorid().intValue()  == creatorid.intValue() && x.getTicketid().intValue() == ticketid.intValue())
                .findAny()
                .orElse(null);
    }

    @Override
    public Ticket getTicket(Integer ticketid) {
        return tickets.stream()
                .filter(x -> x.getTicketid().intValue() == ticketid.intValue())
                .findAny()
                .orElse(null);
    }

    @Override
    public void updateTicket(Integer ticketid, String content, Integer
            severity, Integer status) {
        Ticket ticket = getTicket(ticketid);
        if(ticket == null){
            throw new RuntimeException("Ticket Not Available");
        }
        ticket.setContent(content);
        ticket.setSeverity(severity);
        ticket.setStatus(status);
    }



}
