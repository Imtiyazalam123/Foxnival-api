package com.foxnival.constant;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum PaymentStatus {

    SUCCESS("success"),

    FAILED("failed");

    @JsonValue
    private String paymentStatus;
}
