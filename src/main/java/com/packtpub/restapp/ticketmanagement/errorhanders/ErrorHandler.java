package com.packtpub.restapp.ticketmanagement.errorhanders;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.LinkedHashMap;
import java.util.Map;

public class ErrorHandler {
    @ExceptionHandler(Exception.class)
    public @ResponseBody
    <T> T handleException(Exception ex) {
        Map<String, Object> errorMap = new LinkedHashMap<>();
        if(ex instanceof
                org.springframework.web.bind.MissingServletRequestParameterException){
            errorMap.put("Parameter Missing", ex.getMessage());
            return (T) errorMap;
        }
        errorMap.put("Generic Error ", ex.getMessage());
        return (T) errorMap;
    }
}
