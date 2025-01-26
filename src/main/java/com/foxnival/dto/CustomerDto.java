package com.foxnival.dto;

import com.foxnival.constant.Source;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {

    private Long id;

    private Long subscriberId;

    private String name;

    private String mobileNo;

    private String purpose;

    private Source source;

    private String email;

    private String comments;

    private Instant createdDate;

    private Instant lastModifiedDate;
}
