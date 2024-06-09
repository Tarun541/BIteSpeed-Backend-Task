package com.bitespeed.contact.common.tables.services;

import com.bitespeed.contact.common.bean.response.ContactResponse;
import com.bitespeed.contact.common.tables.Contact;
import com.bitespeed.contact.common.utilities.Utility;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ResponseMapper {


    public ContactResponse getResponse(Contact contact){
        List<String> emails=new ArrayList<>();
        List<String> phoneNumber=new ArrayList<>();
        List<Long> secondaryContactIds=new ArrayList<>();

        ContactResponse contactResponse=new ContactResponse();
        emails.add(contact.getEmail());
        phoneNumber.add(contact.getPhoneNumber());
        contactResponse.setEmails(emails);
        contactResponse.setPhoneNumbers(phoneNumber);
        contactResponse.setPrimaryContatctId(contact.getId());
        contactResponse.setSecondaryContactIds(secondaryContactIds);
        return  contactResponse;
    }

    public ContactResponse getResponse(Contact primary, List<Contact> secondaryList){
        List<String> emails=new ArrayList<>();
        List<String> phoneNumber=new ArrayList<>();
        List<Long> secondaryContactIds=new ArrayList<>();

        ContactResponse contactResponse=new ContactResponse();
        contactResponse.setPrimaryContatctId(primary.getId());
        if(!Utility.isNullOrEmpty(primary.getEmail())){
            emails.add(primary.getEmail());
        }
        if(!Utility.isNullOrEmpty(primary.getPhoneNumber())){
            phoneNumber.add(primary.getPhoneNumber());
        }
        for(Contact contact: secondaryList){
            if(!Utility.isNullOrEmpty(contact.getEmail()) && !emails.contains(contact.getEmail())){
                emails.add(contact.getEmail());
            }
            if(!Utility.isNullOrEmpty(contact.getPhoneNumber()) && !phoneNumber.contains(contact.getPhoneNumber())){
                phoneNumber.add(contact.getPhoneNumber());
            }
            secondaryContactIds.add(contact.getId());
        }
        contactResponse.setEmails(emails);
        contactResponse.setPhoneNumbers(phoneNumber);
        contactResponse.setSecondaryContactIds(secondaryContactIds);
        return  contactResponse;
    }
}
