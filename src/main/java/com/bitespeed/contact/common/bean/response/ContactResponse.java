package com.bitespeed.contact.common.bean.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


public class ContactResponse {


    @Getter
    @Setter
    @JsonProperty("primaryContatctId")
    private Long primaryContatctId;

    @Getter
    @Setter
    @JsonProperty("emails")
    private List<String> emails;

    @Getter
    @Setter
    @JsonProperty("phoneNumbers")
    private List<String> phoneNumbers;

    @Getter
    @Setter
    @JsonProperty("secondaryContactIds")
    private List<Long> secondaryContactIds;
}
