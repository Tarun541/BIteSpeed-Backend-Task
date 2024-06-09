package com.bitespeed.contact.common.tables;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

@Entity
@Table(name = "contacts")
public class Contact {

    @Id @Getter @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter @Setter
    @Column(name = "phone_number")
    private String phoneNumber;

    @Getter @Setter
    @Column(name = "email")
    private String email;

    @Getter @Setter
    @Column(name = "linked_id")
    private Long linkedId;

    @Getter @Setter
    @Column(name = "link_precedence")
    private String linkPrecedence;

    @Getter @Setter
    @Column(name = "created_at", nullable = false, updatable = false)
    private ZonedDateTime createdAt;

    @Getter @Setter
    @Column(name = "updated_at")
    private ZonedDateTime updatedAt;

    @Getter @Setter
    @Column(name = "deleted_at")
    private ZonedDateTime deletedAt;

}

