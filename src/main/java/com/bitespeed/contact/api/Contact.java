package com.bitespeed.contact.api;

import com.bitespeed.contact.common.bean.response.ContactResponse;
import com.bitespeed.contact.common.tables.ContactRepository;
import com.bitespeed.contact.common.tables.services.ContactService;
import com.bitespeed.contact.common.utilities.Utility;
import com.bitespeed.contact.common.bean.request.ContactRequest;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Log4j2
@RestController
public class Contact {

    @Autowired
    private ContactRepository contactRepository;

    @CrossOrigin
    @PostMapping(value = "/identify", produces = "application/json")
    public Object get(@RequestBody ContactRequest request, @RequestHeader Map<String, String> headers, HttpServletResponse response){
        try{
            log.info("Request Recieved:"+ Utility.toJson(request));
            if(Utility.isNullOrEmpty(request.getEmail()) && Utility.isNullOrEmpty(request.getPhoneNumber())){
                Map<String,String> errorResponse=new HashMap<>();
                errorResponse.put("error_message","Not a Valid Request");
                response.setStatus(400);
                return errorResponse;
            }
            ContactService contactService=new ContactService(contactRepository);
            ContactResponse consolidatedContact = contactService.consolidateContact(request.getEmail(), request.getPhoneNumber());
            Map<String,Object> result=new HashMap<>();
            result.put("contact",consolidatedContact);
            return result;
        }catch (Exception e){
            log.error(e);
            Map<String,String> errorResponse=new HashMap<>();
            errorResponse.put("error_message","generic error occured. Please try again");
            response.setStatus(500);
            return errorResponse;
        }
    }
}


