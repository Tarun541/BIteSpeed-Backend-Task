package com.bitespeed.contact.api;

import com.bitespeed.contact.utilities.Utility;
import com.bitespeed.contact.beans.request.ContactRequest;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Log4j2
@RestController
public class Contact {

    @CrossOrigin
    @PostMapping(value = "/identify", produces = "application/json")
    public Object get(@RequestBody ContactRequest request, @RequestHeader Map<String, String> headers, HttpServletResponse response){
        log.info("Request Recieved:"+ Utility.toJson(request));
        return "Success";
    }
}


