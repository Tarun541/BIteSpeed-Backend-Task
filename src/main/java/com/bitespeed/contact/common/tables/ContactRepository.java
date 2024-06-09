package com.bitespeed.contact.common.tables;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface ContactRepository extends JpaRepository<Contact, Long> {
    List<Contact> findByPhoneNumber(String phoneNumber);
    List<Contact> findByEmail(String email);
    Optional<Contact> findByPhoneNumberAndEmail(String phoneNumber, String email);

    List<Contact> findByLinkedId(Long linkedId);

    Optional<Contact> findById(Long id);
}

