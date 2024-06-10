package com.bitespeed.contact.api;

import com.bitespeed.contact.common.bean.request.ContactRequest;
import com.bitespeed.contact.common.tables.ContactRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Log4j2
@RestController
public class GetAllContacts {

    @Autowired
    private ContactRepository contactRepository;

    @CrossOrigin
    @GetMapping(value = "/get/identities", produces = "application/json")
    public Object get(@RequestHeader Map<String, String> headers, HttpServletResponse response){
            log.info("Get Request Received");
            return contactRepository.findAll();
    }
}
