package com.packtpub.restapp.ticketmanagement.controllers;

import com.packtpub.restapp.ticketmanagement.aop.UserTokenRequired;
import com.packtpub.restapp.ticketmanagement.model.User;
import com.packtpub.restapp.ticketmanagement.service.TicketService;
import com.packtpub.restapp.ticketmanagement.service.UserService;
import com.packtpub.restapp.ticketmanagement.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.LinkedHashMap;
import java.util.Map;

import static com.packtpub.restapp.ticketmanagement.util.Util.getUserNotAvailableError;

@RestController
@RequestMapping("/ticket")
public class TicketController {

    @Autowired
    UserService userService;

    @Autowired
    TicketService ticketService;
    /*
     * Rule:
     * Only user can create a ticket
     */
    @SuppressWarnings("unchecked")
    @ResponseBody
    @UserTokenRequired
    @RequestMapping(value = "", method = RequestMethod.POST)
    public <T> T addTicket(
            @RequestParam(value="content") String content,
            HttpServletRequest request
    ) {
        User user = userService.getUserByToken(request.getHeader("token"));
        ticketService.addTicket(user.getUserid(), content, 2, 1);
        return Util.getSuccessResult();
    }

    @ResponseBody
    @RequestMapping("/my/tickets")
    public Map<String, Object> getMyTickets(
            HttpServletRequest request
    ) {
        User user = userService.getUserByToken(request.getHeader("token"));
        if(user == null){
            return getUserNotAvailableError();
        }
        return
                Util.getSuccessResult(ticketService.getMyTickets(user.getUserid()));
    }

    @ResponseBody
    @UserTokenRequired
    @RequestMapping("/{ticketid}")
    public <T> T getTicket(
            @PathVariable("ticketid") final Integer ticketid,
            HttpServletRequest request
    ) {

        return (T) Util.getSuccessResult(ticketService.getTicket(ticketid));
    }

    @ResponseBody
    @RequestMapping(value = "/{ticketid}", method = RequestMethod.PUT)
    public <T> T updateTicketByCustomer (
            @PathVariable("ticketid") final Integer ticketid,
            @RequestParam(value="content") String content,
            HttpServletRequest request,
            HttpServletResponse response
    ) {
        User user = userService.getUserByToken(request.getHeader("token"));
        if(user == null){
            return getUserNotAvailableError();
        }
        ticketService.updateTicket(ticketid, content, 2, 1);
        Map<String, String> result = new LinkedHashMap<>();
        result.put("result", "updated");
        return (T) result;
    }


}
