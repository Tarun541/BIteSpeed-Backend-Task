package com.bitespeed.contact.common.tables.services;


import com.bitespeed.contact.common.bean.response.ContactResponse;
import com.bitespeed.contact.common.tables.Contact;
import com.bitespeed.contact.common.tables.ContactRepository;
import com.bitespeed.contact.common.utilities.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ContactService {

    private ContactRepository contactRepository;

    ResponseMapper responseMapper=new ResponseMapper();

    public ContactService(ContactRepository contactRepository){
        this.contactRepository=contactRepository;
    }

    @Transactional
    public ContactResponse consolidateContact(String email, String phoneNumber) {

        Optional<Contact> contact=contactRepository.findByPhoneNumberAndEmail(phoneNumber,email);
        List<Contact> emailMatches=contactRepository.findByEmail(email);
        List<Contact> phoneMatches=contactRepository.findByPhoneNumber(phoneNumber);

        if(contact.isPresent()){
            //if a record exist with email and phone number
            if("primary".equals(contact.get().getLinkPrecedence())){
                List<Contact> secondaryList = contactRepository.findByLinkedId(contact.get().getId());
                return responseMapper.getResponse(contact.get(),secondaryList);
            } else {
                Optional<Contact> primary = contactRepository.findById(contact.get().getLinkedId());
                List<Contact> secondaryList = contactRepository.findByLinkedId(contact.get().getLinkedId());
                return responseMapper.getResponse(primary.get(), secondaryList);
            }
        }else if(emailMatches.size()!=0 && phoneMatches.size()!=0){
            // two records exist with email matching with one record and phonenumber with another record
            Contact emailMatch = emailMatches.get(0);
            Contact phoneMatch = phoneMatches.get(0);
            if(emailMatch.getCreatedAt().isBefore(phoneMatch.getCreatedAt())){
                phoneMatch.setLinkPrecedence("secondary");
                phoneMatch.setLinkedId(emailMatch.getId());
                contactRepository.save(phoneMatch);
                return responseMapper.getResponse(emailMatch,phoneMatches);
            }else{
                emailMatch.setLinkPrecedence("secondary");
                emailMatch.setLinkedId(phoneMatch.getId());
                contactRepository.save(emailMatch);
                return responseMapper.getResponse(phoneMatch,emailMatches);
            }
        }else if(emailMatches.size()!=0){
            //record exist with only email matches
            Contact emailMatch=emailMatches.get(0);
            if(!Utility.isNullOrEmpty(phoneNumber)){
                createNewContact(phoneNumber,email,emailMatch);
            }
            if("primary".equals(emailMatch.getLinkPrecedence())){
                List<Contact> secondaryList = contactRepository.findByLinkedId(emailMatch.getId());
                return responseMapper.getResponse(emailMatch,secondaryList);
            }else{
                Optional<Contact> primary = contactRepository.findById(emailMatch.getLinkedId());
                List<Contact> secondaryList = contactRepository.findByLinkedId(emailMatch.getLinkedId());
                return responseMapper.getResponse(primary.get(), secondaryList);
            }
        }else if(phoneMatches.size()!=0){
            //record exists with phonenumber matching
            Contact phoneMatch=phoneMatches.get(0);
            if(!Utility.isNullOrEmpty(email)){
                createNewContact(phoneNumber,email,phoneMatch);
            }
            if("primary".equals(phoneMatch.getLinkPrecedence())){
                List<Contact> secondaryList = contactRepository.findByLinkedId(phoneMatch.getId());
                return responseMapper.getResponse(phoneMatch,secondaryList);
            }else{
                Optional<Contact> primary = contactRepository.findById(phoneMatch.getLinkedId());
                List<Contact> secondaryList = contactRepository.findByLinkedId(phoneMatch.getLinkedId());
                return responseMapper.getResponse(primary.get(), secondaryList);
            }
        }else{
            //no record exist with either email or phone number
            Contact contact1=createNewContact(phoneNumber,email);
            return responseMapper.getResponse(contact1);
        }

    }


    private Contact createNewContact(String phoneNumber, String email, Contact primaryContact) {
        // creating a record with secondary status
        Contact newContact = new Contact();
        newContact.setPhoneNumber(phoneNumber);
        newContact.setEmail(email);
        if("primary".equals(primaryContact.getLinkPrecedence())){
            newContact.setLinkedId(primaryContact.getId());
        }else{
            newContact.setLinkedId(primaryContact.getLinkedId());
        }
        newContact.setLinkPrecedence("secondary");
        newContact.setCreatedAt(Utility.getZonedDateTimeStamp("UTC"));
        newContact.setUpdatedAt(Utility.getZonedDateTimeStamp("UTC"));
        Contact result=contactRepository.save(newContact);
        return result;
    }

    private Contact createNewContact(String phoneNumber, String email) {
        // creating a record with primary status
        Contact newContact = new Contact();
        newContact.setPhoneNumber(phoneNumber);
        newContact.setEmail(email);
        newContact.setLinkedId(null);
        newContact.setLinkPrecedence("primary");
        newContact.setCreatedAt(Utility.getZonedDateTimeStamp("UTC"));
        newContact.setUpdatedAt(Utility.getZonedDateTimeStamp("UTC"));
        Contact result=contactRepository.save(newContact);
        return result;
    }


}

