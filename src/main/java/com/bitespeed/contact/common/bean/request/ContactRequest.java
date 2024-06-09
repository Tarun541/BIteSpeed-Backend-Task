package com.bitespeed.contact.common.bean.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

public class ContactRequest {

    @Getter
    @Setter
    @JsonProperty("email")
    private String email;

    @Getter
    @Setter
    @JsonProperty("phoneNumber")
    private String phoneNumber;

}
