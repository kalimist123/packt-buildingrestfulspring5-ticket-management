package com.packtpub.restapp.ticketmanagement.model;

import java.util.Date;

public class Ticket {

        private Integer ticketid;
        private Integer creatorid;

    public Integer getTicketid() {
        return ticketid;
    }

    public void setTicketid(Integer ticketid) {
        this.ticketid = ticketid;
    }

    public Integer getCreatorid() {
        return creatorid;
    }

    public void setCreatorid(Integer creatorid) {
        this.creatorid = creatorid;
    }

    public Date getCreatedat() {
        return createdat;
    }

    public void setCreatedat(Date createdat) {
        this.createdat = createdat;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getSeverity() {
        return severity;
    }

    public void setSeverity(Integer severity) {
        this.severity = severity;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public static Integer getTicketCounter() {
        return ticketCounter;
    }

    public static void setTicketCounter(Integer ticketCounter) {
        Ticket.ticketCounter = ticketCounter;
    }

    private Date createdat;
        private String content;
        private Integer severity;
        private Integer status;
        // getter and setter methods
        @Override
        public String toString() {
            return "Ticket [ticketid=" + ticketid + ", creatorid=" + creatorid
                    + ", createdat=" + createdat + ", content=" + content
                    + ", severity=" + severity + ", status=" + status + "]";
        }
        private static Integer ticketCounter = 300;
        public Ticket(Integer creatorid, Date createdat, String content, Integer
                severity, Integer status){
            ticketCounter++;
            this.ticketid = ticketCounter;
            this.creatorid = creatorid;
            this.createdat = createdat;
            this.content = content;
            this.severity = severity;
            this.status = status;
        }
    }


